package com.tmd.dictionary.screen.fragment.search.level2.grammar;

import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.data.source.DataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Listens to user actions from the UI ({@link GrammarFragment}), retrieves the data and updates
 * the UI as required.
 */
final class GrammarPresenter implements GrammarContract.Presenter {
    private static final String TAG = GrammarPresenter.class.getName();
    private final GrammarContract.ViewModel mViewModel;
    private DataSource mRepository;
    private CompositeDisposable mCompositeDisposable;

    public GrammarPresenter(GrammarContract.ViewModel viewModel, DataSource repository) {
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
        Disposable disposable = mRepository.searchGrammar(needSearch)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<Word>() {
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
        mCompositeDisposable.add(disposable);
    }
}
