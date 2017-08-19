package com.tmd.dictionary.screen.fragment.Grammar;

import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.data.source.DataSource;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Listens to user actions from the UI ({@link GrammarFragment}), retrieves the data and updates
 * the UI as required.
 */
final class GrammarPresenter implements GrammarContract.Presenter {
    private static final String TAG = GrammarPresenter.class.getName();
    private final GrammarContract.ViewModel mViewModel;
    private DataSource mRepository;

    public GrammarPresenter(GrammarContract.ViewModel viewModel, DataSource repository) {
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
        mRepository.searchGrammar(needSearch)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new Observer<Word>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mViewModel.onClearData();
                }

                @Override
                public void onNext(@NonNull Word words) {
                    mViewModel.onSearchGrammarSuccess(words);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.onSearchGrammarFailed();
                }

                @Override
                public void onComplete() {
                }
            });
    }
}
