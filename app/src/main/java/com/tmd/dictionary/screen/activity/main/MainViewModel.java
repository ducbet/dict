package com.tmd.dictionary.screen.activity.main;

import android.content.Context;

/**
 * Exposes the data to be used in the Main screen.
 */
public class MainViewModel implements MainContract.ViewModel {
    private MainContract.Presenter mPresenter;
    private Context mContext;

    public MainViewModel(Context context) {
        mContext = context;
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
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public Context getContext() {
        return mContext;
    }
}
