package com.tmd.dictionary.screen.fragment.listboxes;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

import static com.tmd.dictionary.staticfinal.ConstantValue.INT_GRAMMAR;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_JPN_WORD;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_KANJI;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_VIE_WORD;

/**
 * Exposes the data to be used in the JpnBoxes screen.
 */
public class ListBoxesViewModel implements ListBoxesContract.ViewModel {
    private ListBoxesContract.Presenter mPresenter;
    private int mBoxType;
    private ListBoxesAdapter mAdapter;
    private RealmChangeListener mRealmChangeListener =
        new RealmChangeListener<RealmResults>() {
            @Override
            public void onChange(RealmResults boxes) {
                mAdapter.setSource(boxes);
            }
        };

    public ListBoxesViewModel(int boxType) {
        mBoxType = boxType;
        mAdapter = new ListBoxesAdapter(this);
    }

    public ListBoxesAdapter getAdapter() {
        return mAdapter;
    }

    private void getAllFlashcardBoxes() {
        switch (mBoxType) {
            case INT_JPN_WORD:
                mPresenter.getAllJpnBoxes().addChangeListener(mRealmChangeListener);
                break;
            case INT_VIE_WORD:
                mPresenter.getAllVieBoxes().addChangeListener(mRealmChangeListener);
                break;
            case INT_KANJI:
                mPresenter.getAllKanjiBoxes().addChangeListener(mRealmChangeListener);
                break;
            case INT_GRAMMAR:
                mPresenter.getAllGrammarBoxes().addChangeListener(mRealmChangeListener);
                break;
        }
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        getAllFlashcardBoxes();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ListBoxesContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
