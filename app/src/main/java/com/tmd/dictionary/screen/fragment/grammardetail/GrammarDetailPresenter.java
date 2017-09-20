package com.tmd.dictionary.screen.fragment.grammardetail;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.GrammarBox;
import com.tmd.dictionary.data.source.DataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.realm.RealmResults;

/**
 * Listens to user actions from the UI ({@link GrammarDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
final class GrammarDetailPresenter implements GrammarDetailContract.Presenter {
    private static final String TAG = GrammarDetailPresenter.class.getName();
    private final GrammarDetailContract.ViewModel mViewModel;
    private DataSource mRepository;
    private CompositeDisposable mCompositeDisposable;

    public GrammarDetailPresenter(GrammarDetailContract.ViewModel viewModel,
                                  DataSource repository) {
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
    public void saveToHistory(Grammar grammar) {
        mRepository.saveToHistory(grammar);
    }

    @Override
    public void changeLikeState(Grammar grammar) {
        Disposable disposable = mRepository.changeLikeState(grammar)
//            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<Boolean>() {
                @Override
                public void onNext(@NonNull Boolean isLiked) {
                    mViewModel.onSetLiked(isLiked);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                }

                @Override
                public void onComplete() {
                }
            });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void isLiked(Grammar grammar) {
        Disposable disposable = mRepository.isLiked(grammar)
//            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<Boolean>() {
                @Override
                public void onNext(@NonNull Boolean isLiked) {
                    mViewModel.onSetLiked(isLiked);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                }

                @Override
                public void onComplete() {
                }
            });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public RealmResults<GrammarBox> getAllFlashcardBoxes() {
        return mRepository.getAllGrammarBoxes();
    }

    @Override
    public void createFlashcardBox(GrammarBox newBox) {
        mRepository.createFlashcardBox(newBox);
    }
}
