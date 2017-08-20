package com.tmd.dictionary.screen.fragment.search;

import com.tmd.dictionary.data.source.DataSource;

/**
 * Listens to user actions from the UI ({@link SearchFragment}), retrieves the data and updates
 * the UI as required.
 */
final class SearchPresenter implements SearchContract.Presenter {
    private static final String TAG = SearchPresenter.class.getName();
    private final SearchContract.ViewModel mViewModel;

    public SearchPresenter(SearchContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
