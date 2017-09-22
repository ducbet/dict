package com.tmd.dictionary.screen.fragment.learningjpn;

import com.tmd.dictionary.data.model.JpnBox;
import com.tmd.dictionary.screen.fragment.listboxes.ListBoxesContract;

import io.realm.Realm;

/**
 * Exposes the data to be used in the LearningJpn screen.
 */
public class LearningJpnViewModel implements LearningJpnContract.ViewModel {
    private ListBoxesContract.ViewModel mListBoxesViewModel;
    private LearningJpnContract.Presenter mPresenter;
    private Realm mRealm;
    private JpnBox mJpnBox;

    public LearningJpnViewModel(ListBoxesContract.ViewModel listBoxesViewModel, JpnBox jpnBox,
                                Realm realm) {
        mListBoxesViewModel = listBoxesViewModel;
        mJpnBox = jpnBox;
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
    public void setPresenter(LearningJpnContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
