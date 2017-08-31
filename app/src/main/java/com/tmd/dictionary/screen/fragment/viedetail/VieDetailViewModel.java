package com.tmd.dictionary.screen.fragment.viedetail;

import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.screen.activity.main.MainContract;

/**
 * Exposes the data to be used in the VieDetail screen.
 */
public class VieDetailViewModel implements VieDetailContract.ViewModel {
    private MainContract.ViewModel mMainViewModel;
    private VieDetailContract.Presenter mPresenter;
    private VieWord mVieWord;

    public VieDetailViewModel() {
    }

    public VieDetailViewModel(MainContract.ViewModel mainViewModel, VieWord vieWord) {
        mMainViewModel = mainViewModel;
        mVieWord = vieWord;
    }

    public VieWord getVieWord() {
        return mVieWord;
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
    public void setPresenter(VieDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
