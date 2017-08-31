package com.tmd.dictionary.screen.fragment.jpndetail;

import com.tmd.dictionary.data.source.DataSource;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Listens to user actions from the UI ({@link JpnDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
final class JpnDetailPresenter implements JpnDetailContract.Presenter {
    private static final String TAG = JpnDetailPresenter.class.getName();
    private final JpnDetailContract.ViewModel mViewModel;
    private DataSource mRepository;
    private CompositeDisposable mCompositeDisposable;

    public JpnDetailPresenter(JpnDetailContract.ViewModel viewModel, DataSource repository) {
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
}
