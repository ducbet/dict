package com.tmd.dictionary.screen.activity.history;

/**
 * Listens to user actions from the UI ({@link HistoryActivity}), retrieves the data and updates
 * the UI as required.
 */
final class HistoryPresenter implements HistoryContract.Presenter {
    private static final String TAG = HistoryPresenter.class.getName();
    private final HistoryContract.ViewModel mViewModel;

    public HistoryPresenter(HistoryContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
