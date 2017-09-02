package com.tmd.dictionary.screen.activity.history;

import com.tmd.dictionary.data.model.History;
import com.tmd.dictionary.data.source.DataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

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
        Disposable disposable = mRepository.getHistory()
//            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<History>() {
                @Override
                public void onNext(@NonNull History history) {
                    mViewModel.onGetHistorySuccess(history);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.onGetHistoryFailed(e);
                }

                @Override
                public void onComplete() {
                }
            });
        mCompositeDisposable.add(disposable);
    }
}
