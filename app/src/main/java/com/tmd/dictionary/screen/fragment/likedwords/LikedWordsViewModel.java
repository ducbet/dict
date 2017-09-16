package com.tmd.dictionary.screen.fragment.likedwords;

/**
 * Exposes the data to be used in the Liked screen.
 */
public class LikedWordsViewModel implements LikedWordsContract.ViewModel {
    private LikedWordsContract.Presenter mPresenter;

    public LikedWordsViewModel() {
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
    public void setPresenter(LikedWordsContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
