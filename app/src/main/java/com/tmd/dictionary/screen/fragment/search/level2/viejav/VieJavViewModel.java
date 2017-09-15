package com.tmd.dictionary.screen.fragment.search.level2.viejav;

import android.app.Activity;

import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.screen.OnClickSearchedItemListener;
import com.tmd.dictionary.screen.fragment.search.SearchContract;
import com.tmd.dictionary.screen.fragment.search.SearchViewModel;
import com.tmd.dictionary.staticfinal.SoftKeybroad;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Exposes the data to be used in the VieJav screen.
 */
public class VieJavViewModel
    implements VieJavContract.ViewModel, OnClickSearchedItemListener<VieWord> {
    private SearchContract.ViewModel mSearchViewModel;
    private VieJavContract.Presenter mPresenter;
    private String mNeedSearch;
    private VieJpnAdapter mAdapter;
    private RealmChangeListener mRealmChangeListener =
        new RealmChangeListener<RealmResults<VieWord>>() {
            @Override
            public void onChange(RealmResults<VieWord> vieWords) {
                mAdapter.setSource(vieWords);
                if (vieWords.isEmpty()) {
                    onRemoveLastResult();
                }
            }
        };
    private List<RealmResults<VieWord>> mListResults;
    private Realm mRealm;

    public VieJavViewModel(Realm realm, SearchContract.ViewModel searchViewModel) {
        mRealm = realm;
        mSearchViewModel = searchViewModel;
        mAdapter = new VieJpnAdapter(this);
        mListResults = new ArrayList<>();
    }

    public VieJpnAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(VieJavContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onSearchVieJpnSuccess(RealmResults<VieWord> vieWords) {
        vieWords.addChangeListener(mRealmChangeListener);
        mListResults.add(vieWords);
    }

    @Override
    public void onSetNeedSearch(String needSearch) {
        if (!mListResults.isEmpty()) {
            if (needSearch.contains(mNeedSearch)) {
                onChainingQuery(needSearch, mListResults.get(mListResults.size() - 1));
            } else if (mNeedSearch.contains(needSearch)) {
                onChainingQuery(needSearch, mListResults.get(needSearch.length() - 1));
            }
        } else {
            mPresenter.search(needSearch);
        }
        mNeedSearch = needSearch;
    }

    @Override
    public void onChainingQuery(String needSearch, RealmResults<VieWord> parentsResult) {
        mPresenter.chaningQuery(needSearch, parentsResult);
    }

    @Override
    public void onRemoveLastResult() {
        mListResults.get(mListResults.size() - 1).removeAllChangeListeners();
        mListResults.remove(mListResults.size() - 1);
    }

    @Override
    public void onClearRealmResults() {
    }

    @Override
    public void onClick(VieWord vieWord) {
        SoftKeybroad.hide((Activity) ((SearchViewModel) mSearchViewModel).getContext());
        mSearchViewModel.onItemClick(mRealm.copyFromRealm(vieWord));
    }
}
