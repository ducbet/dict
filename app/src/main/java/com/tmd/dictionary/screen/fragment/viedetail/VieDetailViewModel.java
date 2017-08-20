package com.tmd.dictionary.screen.fragment.viedetail;

/**
 * Exposes the data to be used in the VieDetail screen.
 */
public class VieDetailViewModel implements VieDetailContract.ViewModel {
    private VieDetailContract.Presenter mPresenter;

    public VieDetailViewModel() {
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
    public void setPresenter(VieDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
