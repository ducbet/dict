package com.tmd.dictionary.screen.fragment.likedwords;

import com.tmd.dictionary.data.source.DataSource;

/**
 * Listens to user actions from the UI ({@link LikedWordsFragment}), retrieves the data and updates
 * the UI as required.
 */
final class LikedWordsPresenter implements LikedWordsContract.Presenter {
    private static final String TAG = LikedWordsPresenter.class.getName();
    private final LikedWordsContract.ViewModel mViewModel;
    private DataSource mRepository;

    public LikedWordsPresenter(LikedWordsContract.ViewModel viewModel, DataSource repository) {
        mViewModel = viewModel;
        mRepository = repository;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
