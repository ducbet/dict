package com.tmd.dictionary.screen.activity.boxs;

/**
 * Exposes the data to be used in the FlashCardBoxs screen.
 */
public class BoxesViewModel implements BoxesContract.ViewModel {
    private BoxesContract.Presenter mPresenter;

    public BoxesViewModel() {
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
    public void setPresenter(BoxesContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
