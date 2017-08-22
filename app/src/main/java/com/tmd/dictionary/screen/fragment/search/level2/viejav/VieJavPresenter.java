package com.tmd.dictionary.screen.fragment.search.level2.viejav;

import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.data.source.DataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
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
    private Disposable mDisposable;

    public VieJavPresenter(VieJavContract.ViewModel viewModel, DataSource repository) {
        mViewModel = viewModel;
        mRepository = repository;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        mRepository.closeDatabase();
    }

    @Override
    public void search(String needSearch) {
        mViewModel.onClearData();
        mDisposable = mRepository.searchVieJpn(needSearch)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<Word>() {
                @Override
                public void onNext(@NonNull Word words) {
                    mViewModel.onSearchVieJpnSuccess(words);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.onSearchVieJpnFailed();
                }

                @Override
                public void onComplete() {
                }
            });
    }
}
