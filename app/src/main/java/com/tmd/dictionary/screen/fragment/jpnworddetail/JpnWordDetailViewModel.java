package com.tmd.dictionary.screen.fragment.jpnworddetail;

/**
 * Exposes the data to be used in the JpnWordDetail screen.
 */
public class JpnWordDetailViewModel implements JpnWordDetailContract.ViewModel {
    private JpnWordDetailContract.Presenter mPresenter;

    public JpnWordDetailViewModel() {
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
    public void setPresenter(JpnWordDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
