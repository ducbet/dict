package com.tmd.dictionary.screen.fragment.search.level2.kanji;

import android.app.Activity;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.screen.OnClickSearchedItemListener;
import com.tmd.dictionary.screen.fragment.search.SearchContract;
import com.tmd.dictionary.screen.fragment.search.SearchViewModel;
import com.tmd.dictionary.staticfinal.SoftKeybroad;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Exposes the data to be used in the Kanji screen.
 */
public class KanjiViewModel implements KanjiContract.ViewModel, OnClickSearchedItemListener<Kanji> {
    private static final String TAG = KanjiViewModel.class.getName();
    private SearchContract.ViewModel mSearchViewModel;
    private KanjiContract.Presenter mPresenter;
    private String mNeedSearch;
    private KanjiAdapter mAdapter;
    private Realm mRealm;

    public KanjiViewModel(Realm realm, SearchContract.ViewModel searchViewModel) {
        mRealm = realm;
        mSearchViewModel = searchViewModel;
        mAdapter = new KanjiAdapter(this);
    }

    public KanjiAdapter getAdapter() {
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
    public void setPresenter(KanjiContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onSearchKanjiSuccess(RealmResults<Kanji> kanjis) {
        mAdapter.setSource(kanjis);
    }

    @Override
    public void onSetNeedSearch(String needSearch) {
        mNeedSearch = needSearch;
        mPresenter.search(needSearch);
    }

    @Override
    public void onClearRealmResults() {
    }

    @Override
    public void onClick(Kanji kanji) {
        SoftKeybroad.hide((Activity) ((SearchViewModel) mSearchViewModel).getContext());
        mSearchViewModel.onItemClick(mRealm.copyFromRealm(kanji));
    }
}
