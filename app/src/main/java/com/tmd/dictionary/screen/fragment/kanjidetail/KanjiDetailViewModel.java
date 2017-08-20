package com.tmd.dictionary.screen.fragment.kanjidetail;

/**
 * Exposes the data to be used in the KanjiDetail screen.
 */
public class KanjiDetailViewModel implements KanjiDetailContract.ViewModel {
    private KanjiDetailContract.Presenter mPresenter;

    public KanjiDetailViewModel() {
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
    public void setPresenter(KanjiDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
