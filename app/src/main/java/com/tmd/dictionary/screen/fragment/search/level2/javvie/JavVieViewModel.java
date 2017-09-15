package com.tmd.dictionary.screen.fragment.search.level2.javvie;

import android.app.Activity;

import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.screen.OnClickSearchedItemListener;
import com.tmd.dictionary.screen.fragment.search.SearchContract;
import com.tmd.dictionary.screen.fragment.search.SearchViewModel;
import com.tmd.dictionary.staticfinal.SoftKeybroad;
import com.tmd.dictionary.staticfinal.StringHandling;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Exposes the data to be used in the JavVie screen.
 */
public class JavVieViewModel
    implements JavVieContract.ViewModel, OnClickSearchedItemListener<JpnWord> {
    private SearchContract.ViewModel mSearchViewModel;
    private JavVieContract.Presenter mPresenter;
    private String mNeedSearch = "";
    private JavVieAdapter mAdapter;
    private RealmChangeListener mRealmChangeListener =
        new RealmChangeListener<RealmResults<JpnWord>>() {
            @Override
            public void onChange(RealmResults<JpnWord> jpnWords) {
                mAdapter.setSource(jpnWords);
                if (jpnWords.isEmpty()) {
                    onRemoveLastResult();
                }
            }
        };
    private List<RealmResults<JpnWord>> mListResults;
    private Realm mRealm;

    public JavVieViewModel(Realm realm, SearchContract.ViewModel searchViewModel) {
        mRealm = realm;
        mSearchViewModel = searchViewModel;
        mAdapter = new JavVieAdapter(this);
        mListResults = new ArrayList<>();
    }

    public JavVieAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        if (mListResults != null) {
            for (RealmResults<JpnWord> jpnWords : mListResults) {
                jpnWords.removeAllChangeListeners();
            }
        }
    }

    @Override
    public void setPresenter(JavVieContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onSearchJpnVieSuccess(RealmResults<JpnWord> jpnWords) {
        jpnWords.addChangeListener(mRealmChangeListener);
        mListResults.add(jpnWords);
    }

    @Override
    public void onSetNeedSearch(String needSearch) {
        if (!mListResults.isEmpty()) {
            if (needSearch.contains(mNeedSearch)) {
                // mNeedSearch: abc
                // needSearch: abcd
                // -> result of 'abcd' is a child of result of 'abc'
                onChainingQuery(needSearch, mListResults.get(mListResults.size() - 1));
            } else if (mNeedSearch.contains(needSearch)) {
                // mListResults.get(needSearch.length() - 1) is result of 'abc'
                // mNeedSearch: abcd
                // needSearch: abc
                // -> result of 'abc' or 'bcd' is a child of result of 'abc'
                onChainingQuery(needSearch, mListResults.get(needSearch.length() - 1));
            }
        } else {
            // search all database
            if (StringHandling.isWordHasKanjis(needSearch)) {
                mPresenter.searchJpnWordHasKanjis(needSearch);
            } else {
                mPresenter.searchJpnWordNotHasKanjis(needSearch);
            }
        }
        mNeedSearch = needSearch;
    }

    @Override
    public void onChainingQuery(String needSearch, RealmResults<JpnWord> parentsResult) {
        mPresenter.chaningQuery(needSearch, parentsResult);
    }

    @Override
    public void onRemoveLastResult() {
        mListResults.get(mListResults.size() - 1).removeAllChangeListeners();
        mListResults.remove(mListResults.size() - 1);
    }

    @Override
    public void onClearRealmResults() {
        mAdapter.setSource(null);
        mListResults.clear();
    }

    @Override
    public void onClick(JpnWord jpnWord) {
        SoftKeybroad.hide((Activity) ((SearchViewModel) mSearchViewModel).getContext());
        mSearchViewModel.onItemClick(mRealm.copyFromRealm(jpnWord));
    }
}
