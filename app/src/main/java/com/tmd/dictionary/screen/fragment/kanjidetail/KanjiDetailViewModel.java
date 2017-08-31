package com.tmd.dictionary.screen.fragment.kanjidetail;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.screen.activity.main.MainContract;

/**
 * Exposes the data to be used in the KanjiDetail screen.
 */
public class KanjiDetailViewModel implements KanjiDetailContract.ViewModel {
    private MainContract.ViewModel mMainViewModel;
    private KanjiDetailContract.Presenter mPresenter;
    private Kanji mKanji;

    public KanjiDetailViewModel(MainContract.ViewModel mainViewModel, Kanji kanji) {
        mMainViewModel = mainViewModel;
        mKanji = kanji;
    }

    public Kanji getKanji() {
        return mKanji;
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
