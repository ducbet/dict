package com.tmd.dictionary.screen.fragment.jpnboxes;

/**
 * Exposes the data to be used in the JpnBoxes screen.
 */
public class JpnBoxesViewModel implements JpnBoxesContract.ViewModel {
    private JpnBoxesContract.Presenter mPresenter;

    public JpnBoxesViewModel() {
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
    public void setPresenter(JpnBoxesContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
