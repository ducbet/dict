package com.tmd.dictionary.screen.fragment.grammardetail;

/**
 * Exposes the data to be used in the GrammarDetail screen.
 */
public class GrammarDetailViewModel implements GrammarDetailContract.ViewModel {
    private GrammarDetailContract.Presenter mPresenter;

    public GrammarDetailViewModel() {
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
    public void setPresenter(GrammarDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
