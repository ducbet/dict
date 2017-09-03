package com.tmd.dictionary.screen.activity.history;

import android.content.Context;

import com.tmd.dictionary.data.model.History;

import io.realm.RealmChangeListener;

import static com.tmd.dictionary.staticfinal.ConstantValue.INT_GRAMMAR;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_JPN_WORD;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_KANJI;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_VIE_WORD;

/**
 * Exposes the data to be used in the History screen.
 */
public class HistoryViewModel implements HistoryContract.ViewModel {
    private HistoryContract.Presenter mPresenter;
    private Context mContext;
    private HistoryAdapter mAdapter;
    private RealmChangeListener mRealmChangeListener =
        new RealmChangeListener<History>() {
            @Override
            public void onChange(History history) {
                mAdapter.setSource(history);
            }
        };
    private History mHistory;

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

    @Override
    public void onGetHistoryFailed(Throwable e) {
    }

    @Override
    public void onItemClick(Integer type, String key) {
        switch (type) {
            case INT_JPN_WORD:
                break;
            case INT_VIE_WORD:
                break;
            case INT_KANJI:
                break;
            case INT_GRAMMAR:
                break;
            default:
                break;
        }
    }
}
