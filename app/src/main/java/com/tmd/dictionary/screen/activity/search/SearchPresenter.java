package com.tmd.dictionary.screen.activity.search;

import com.tmd.dictionary.data.source.DataSource;

import java.util.List;

/**
 * Listens to user actions from the UI ({@link SearchActivity}), retrieves the data and updates
 * the UI as required.
 */
final class SearchPresenter implements SearchContract.Presenter {
    private static final String TAG = SearchPresenter.class.getName();
    private final SearchContract.ViewModel mViewModel;
    private DataSource mRepository;

    public SearchPresenter(SearchContract.ViewModel viewModel, DataSource repository) {
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
