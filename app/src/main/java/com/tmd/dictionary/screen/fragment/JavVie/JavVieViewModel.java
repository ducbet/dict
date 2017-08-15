package com.tmd.dictionary.screen.fragment.JavVie;

import com.tmd.dictionary.screen.activity.search.SearchContract;

/**
 * Exposes the data to be used in the JavVie screen.
 */
public class JavVieViewModel implements JavVieContract.ViewModel {
    private SearchContract.ViewModel mSearchViewModel;
    private JavVieContract.Presenter mPresenter;

    public JavVieViewModel(SearchContract.ViewModel searchViewModel) {
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
    public void setPresenter(JavVieContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
