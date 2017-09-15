package com.tmd.dictionary.screen.fragment.history;

import com.tmd.dictionary.data.source.DataSource;

/**
 * Listens to user actions from the UI ({@link HistoryFragment}), retrieves the data and updates
 * the UI as required.
 */
final class HistoryPresenter implements HistoryContract.Presenter {
    private static final String TAG = HistoryPresenter.class.getName();
    private final HistoryContract.ViewModel mViewModel;
    private DataSource mRepository;

    public HistoryPresenter(HistoryContract.ViewModel viewModel, DataSource repository) {
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
    public void getHistory() {
        mViewModel.onGetHistorySuccess(mRepository.getHistory());
    }
}
