package com.tmd.dictionary.screen.fragment.search.level2.viejav;

import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.data.source.DataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.realm.RealmResults;

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
            mCompositeDisposable.dispose();
        }
    }

    @Override
    public void search(String needSearch) {
        Disposable disposable = mRepository.searchVieJpn(needSearch)
//            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<RealmResults<VieWord>>() {
                @Override
                public void onNext(@NonNull RealmResults<VieWord> vieWords) {
                    mViewModel.onSearchVieJpnSuccess(vieWords);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.onSearchVieJpnFailed();
                }

                @Override
                public void onComplete() {
                }
            });
        mCompositeDisposable.add(disposable);
    }
}
