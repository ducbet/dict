package com.tmd.dictionary.screen.fragment.kanjidetail;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.KanjiBox;
import com.tmd.dictionary.data.source.DataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.realm.RealmResults;

/**
 * Listens to user actions from the UI ({@link KanjiDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
final class KanjiDetailPresenter implements KanjiDetailContract.Presenter {
    private static final String TAG = KanjiDetailPresenter.class.getName();
    private final KanjiDetailContract.ViewModel mViewModel;
    private DataSource mRepository;
    private CompositeDisposable mCompositeDisposable;

    public KanjiDetailPresenter(KanjiDetailContract.ViewModel viewModel, DataSource repository) {
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
    public void saveToHistory(Kanji kanji) {
        mRepository.saveToHistory(kanji);
    }

    @Override
    public void changeLikeState(Kanji kanji) {
        Disposable disposable = mRepository.changeLikeState(kanji)
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
    public void isLiked(Kanji kanji) {
        Disposable disposable = mRepository.isLiked(kanji)
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
    public RealmResults<KanjiBox> getAllFlashcardBoxes() {
        return mRepository.getAllKanjiBoxes();
    }

    @Override
    public void createFlashcardBox(KanjiBox newBox) {
        mRepository.createFlashcardBox(newBox);
    }
}
