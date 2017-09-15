package com.tmd.dictionary.screen.fragment.history;

import com.tmd.dictionary.data.model.History;

import io.realm.RealmChangeListener;

/**
 * Exposes the data to be used in the History screen.
 */
public class HistoryViewModel implements HistoryContract.ViewModel {
    private HistoryContract.Presenter mPresenter;
    private HistoryAdapter mAdapter;
    private RealmChangeListener mRealmChangeListener =
        new RealmChangeListener<History>() {
            @Override
            public void onChange(History history) {
                mAdapter.setSource(history);
            }
        };
    private History mHistory;

    public HistoryViewModel() {
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
        if (mHistory.isValid()) {
            mHistory.removeAllChangeListeners();
        }
    }

    @Override
    public void setPresenter(HistoryContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetHistorySuccess(History history) {
        mHistory = history;
        mHistory.addChangeListener(mRealmChangeListener);
    }
}
