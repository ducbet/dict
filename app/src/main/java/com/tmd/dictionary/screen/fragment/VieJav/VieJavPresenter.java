package com.tmd.dictionary.screen.fragment.VieJav;

import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.data.source.DataSource;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Listens to user actions from the UI ({@link VieJavFragment}), retrieves the data and updates
 * the UI as required.
 */
final class VieJavPresenter implements VieJavContract.Presenter {
    private static final String TAG = VieJavPresenter.class.getName();
    private final VieJavContract.ViewModel mViewModel;
    private DataSource mRepository;

    public VieJavPresenter(VieJavContract.ViewModel viewModel, DataSource repository) {
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
    public void search(String needSearch) {
        mRepository.searchVieJpn(needSearch)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new Observer<Word>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mViewModel.onClearData();
                }

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
