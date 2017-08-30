package com.tmd.dictionary.screen.fragment.search.level2.kanji;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.screen.fragment.search.SearchContract;

import io.realm.RealmResults;

/**
 * Exposes the data to be used in the Kanji screen.
 */
public class KanjiViewModel implements KanjiContract.ViewModel {
    private static final String TAG = KanjiViewModel.class.getName();
    private SearchContract.ViewModel mSearchViewModel;
    private KanjiContract.Presenter mPresenter;
    private String mNeedSearch;
    private KanjiAdapter mAdapter;

    public KanjiViewModel(SearchContract.ViewModel searchViewModel) {
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
    public void onSearchKanjiFailed() {
    }

    @Override
    public void onSetNeedSearch(String needSearch) {
        mNeedSearch = needSearch;
        mPresenter.search(needSearch);
    }

    @Override
    public void onClearData() {
        mAdapter.clearData();
    }

    @Override
    public void onItemClick(Kanji kanji) {
        mSearchViewModel.onItemClick(kanji);
    }
}
