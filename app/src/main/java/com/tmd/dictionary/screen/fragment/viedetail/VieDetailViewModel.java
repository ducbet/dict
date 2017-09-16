package com.tmd.dictionary.screen.fragment.viedetail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tmd.dictionary.BR;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.screen.activity.main.MainContract;

/**
 * Exposes the data to be used in the VieDetail screen.
 */
public class VieDetailViewModel extends BaseObservable implements VieDetailContract.ViewModel {
    private MainContract.ViewModel mMainViewModel;
    private VieDetailContract.Presenter mPresenter;
    private VieWord mVieWord;
    private boolean mIsLiked;

    public VieDetailViewModel(MainContract.ViewModel mainViewModel, VieWord vieWord) {
        mMainViewModel = mainViewModel;
        mVieWord = vieWord;
    }

    public VieWord getVieWord() {
        return mVieWord;
    }

    @Bindable
    public boolean isLiked() {
        return mIsLiked;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.isLiked(mVieWord.getOrigin());
        mPresenter.saveToHistory(mVieWord);
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(VieDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onChangeLikeState() {
        mPresenter.changeLikeState(mVieWord);
    }

    @Override
    public void onSetLiked(Boolean isLiked) {
        mIsLiked = isLiked;
        notifyPropertyChanged(BR.liked);
    }
}
