package com.tmd.dictionary.screen.activity.history;

import android.content.Context;

import com.tmd.dictionary.data.model.History;

/**
 * Exposes the data to be used in the History screen.
 */
public class HistoryViewModel implements HistoryContract.ViewModel {
    private HistoryContract.Presenter mPresenter;
    private Context mContext;
    private HistoryAdapter mAdapter;

    public HistoryViewModel(Context context) {
        mContext = context;
        mAdapter = new HistoryAdapter(this);
    }

    public HistoryAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getHistory();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(HistoryContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetHistorySuccess(History history) {
        mAdapter.setSource(history);
    }

    @Override
    public void onGetHistoryFailed(Throwable e) {
    }
}
