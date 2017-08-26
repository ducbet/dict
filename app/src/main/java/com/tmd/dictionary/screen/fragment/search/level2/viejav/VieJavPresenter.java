package com.tmd.dictionary.screen.fragment.search.level2.viejav;

import android.util.Log;

import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.data.source.DataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Listens to user actions from the UI ({@link VieJavFragment}), retrieves the data and updates
 * the UI as required.
 */
final class VieJavPresenter implements VieJavContract.Presenter {
    private static final String TAG = VieJavPresenter.class.getName();
    private final VieJavContract.ViewModel mViewModel;
    private DataSource mRepository;
    private CompositeDisposable mCompositeDisposable;

    public VieJavPresenter(VieJavContract.ViewModel viewModel, DataSource repository) {
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
            mCompositeDisposable.clear();
        }
        mRepository.closeDatabase();
    }

    @Override
    public void search(String needSearch) {
        mViewModel.onClearData();
        Disposable disposable = mRepository.searchVieJpn(needSearch)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<Word>() {
                @Override
                public void onNext(@NonNull Word words) {
                    mViewModel.onSearchVieJpnSuccess(words);
                    Log.e(TAG, "onNext: ");
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.onSearchVieJpnFailed();
                    Log.e(TAG, "onError: " + e.getMessage());
                }

                @Override
                public void onComplete() {
                }
            });
        mCompositeDisposable.add(disposable);
    }
}
