package com.tmd.dictionary.screen.activity.history;

/**
 * Exposes the data to be used in the History screen.
 */
public class HistoryViewModel implements HistoryContract.ViewModel {
    private HistoryContract.Presenter mPresenter;

    public HistoryViewModel() {
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
    public void setPresenter(HistoryContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
