package com.tmd.dictionary.screen.fragment.kanjidetail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tmd.dictionary.BR;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.screen.activity.main.MainContract;

/**
 * Exposes the data to be used in the KanjiDetail screen.
 */
public class KanjiDetailViewModel extends BaseObservable implements KanjiDetailContract.ViewModel {
    private MainContract.ViewModel mMainViewModel;
    private KanjiDetailContract.Presenter mPresenter;
    private Kanji mKanji;
    private boolean mIsLiked;

    public KanjiDetailViewModel(MainContract.ViewModel mainViewModel, Kanji kanji) {
        mMainViewModel = mainViewModel;
        mKanji = kanji;
    }

    public Kanji getKanji() {
        return mKanji;
    }

    @Bindable
    public boolean isLiked() {
        return mIsLiked;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.isLiked(mKanji.getOrigin());
        mPresenter.saveToHistory(mKanji);
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(KanjiDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onChangeLikeState() {
        mPresenter.changeLikeState(mKanji.getOrigin());
    }

    @Override
    public void onSetLiked(Boolean isLiked) {
        mIsLiked = isLiked;
        notifyPropertyChanged(BR.liked);
    }
}
