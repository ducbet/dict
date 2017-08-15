package com.tmd.dictionary.screen.fragment.Kanji;

import com.tmd.dictionary.screen.activity.search.SearchContract;

/**
 * Exposes the data to be used in the Kanji screen.
 */
public class KanjiViewModel implements KanjiContract.ViewModel {
    private SearchContract.ViewModel mSearchViewModel;
    private KanjiContract.Presenter mPresenter;

    public KanjiViewModel(SearchContract.ViewModel searchViewModel) {
        mSearchViewModel = searchViewModel;
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
}
