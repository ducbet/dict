package com.tmd.dictionary.screen.fragment.level2.javvie;

import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.data.source.DataSource;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Listens to user actions from the UI ({@link JavVieFragment}), retrieves the data and updates
 * the UI as required.
 */
final class JavViePresenter implements JavVieContract.Presenter {
    private static final String TAG = JavViePresenter.class.getName();
    private final JavVieContract.ViewModel mViewModel;
    private DataSource mRepository;

    public JavViePresenter(JavVieContract.ViewModel viewModel, DataSource repository) {
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
    public void search(final String needSearch) {
        mRepository.searchJpnVie(needSearch)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new Observer<Word>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mViewModel.onClearData();
                }

                @Override
                public void onNext(@NonNull Word words) {
                    mViewModel.onSearchJpnVieSuccess(words);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.onSearchJpnVieFailed();
                }

                @Override
                public void onComplete() {
                }
            });
    }
}
