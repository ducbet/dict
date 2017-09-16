package com.tmd.dictionary.screen.fragment.viedetail;

import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.data.source.DataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Listens to user actions from the UI ({@link VieDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
final class VieDetailPresenter implements VieDetailContract.Presenter {
    private static final String TAG = VieDetailPresenter.class.getName();
    private final VieDetailContract.ViewModel mViewModel;
    private DataSource mRepository;
    private CompositeDisposable mCompositeDisposable;

    public VieDetailPresenter(VieDetailContract.ViewModel viewModel, DataSource repository) {
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
    public void saveToHistory(VieWord vieWord) {
        mRepository.saveToHistory(vieWord);
    }

    @Override
    public void changeLikeState(VieWord vieWord) {
        Disposable
            disposable = mRepository.changeLikeState(vieWord)
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
    public void isLiked(String key) {
        Disposable disposable = mRepository.isLiked(key)
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
}
