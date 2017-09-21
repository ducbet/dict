package com.tmd.dictionary.screen.activity.boxs;

import android.content.Context;

import io.realm.Realm;

/**
 * Exposes the data to be used in the FlashCardBoxs screen.
 */
public class BoxesViewModel implements BoxesContract.ViewModel {
    private BoxesContract.Presenter mPresenter;
    private Context mContext;
    private Realm mRealm;

    public BoxesViewModel(Context context, Realm realm) {
        mContext = context;
        mRealm = realm;
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

    @Override
    public void onJpnBoxesClicked() {
    }

    @Override
    public void onVieBoxesClicked() {
    }

    @Override
    public void onKanjiBoxesClicked() {
    }

    @Override
    public void onGrammarBoxesClicked() {
    }
}
