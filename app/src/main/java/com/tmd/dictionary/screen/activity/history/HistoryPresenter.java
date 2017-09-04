package com.tmd.dictionary.screen.activity.history;

import com.tmd.dictionary.data.source.DataSource;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Listens to user actions from the UI ({@link HistoryActivity}), retrieves the data and updates
 * the UI as required.
 */
final class HistoryPresenter implements HistoryContract.Presenter {
    private static final String TAG = HistoryPresenter.class.getName();
    private final HistoryContract.ViewModel mViewModel;
    private DataSource mRepository;
    private CompositeDisposable mCompositeDisposable;

    public HistoryPresenter(HistoryContract.ViewModel viewModel, DataSource repository) {
        mViewModel = viewModel;
        mRepository = repository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    @Override
    public void getHistory() {
        mViewModel.onGetHistorySuccess(mRepository.getHistory());
    }
}
