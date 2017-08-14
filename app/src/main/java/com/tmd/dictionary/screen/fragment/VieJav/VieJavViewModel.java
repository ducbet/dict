package com.tmd.dictionary.screen.fragment.VieJav;

import com.tmd.dictionary.screen.activity.search.SearchContract;

/**
 * Exposes the data to be used in the VieJav screen.
 */
public class VieJavViewModel implements VieJavContract.ViewModel {
    private SearchContract.ViewModel mSearchViewModel;
    private VieJavContract.Presenter mPresenter;

    public VieJavViewModel(SearchContract.ViewModel searchViewModel) {
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
    public void setPresenter(VieJavContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
