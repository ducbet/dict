package com.tmd.dictionary.screen.activity.main;

import com.tmd.dictionary.data.source.DataSource;
import com.tmd.dictionary.data.source.Repository;

/**
 * Listens to user actions from the UI ({@link MainActivity}), retrieves the data and updates
 * the UI as required.
 */
final class MainPresenter implements MainContract.Presenter {
    private static final String TAG = MainPresenter.class.getName();
    private final MainContract.ViewModel mViewModel;
    private DataSource mRepository;

    public MainPresenter(MainContract.ViewModel viewModel, Repository repository) {
        mViewModel = viewModel;
        mRepository = repository;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void createHistoryObjectIfNotExist() {
        mRepository.createHistoryObjectIfNotExist();
    }

    @Override
    public void createLikedWordObjectIfNotExist() {
        mRepository.createLikedWordObjectIfNotExist();
    }
}
