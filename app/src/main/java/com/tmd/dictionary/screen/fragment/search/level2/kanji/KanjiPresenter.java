package com.tmd.dictionary.screen.fragment.search.level2.kanji;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.source.DataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.realm.RealmResults;

/**
 * Listens to user actions from the UI ({@link KanjiFragment}), retrieves the data and updates
 * the UI as required.
 */
final class KanjiPresenter implements KanjiContract.Presenter {
    private static final String TAG = KanjiPresenter.class.getName();
    private final KanjiContract.ViewModel mViewModel;
    private DataSource mRepository;
    private CompositeDisposable mCompositeDisposable;

    public KanjiPresenter(KanjiContract.ViewModel viewModel, DataSource repository) {
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
    public void search(final String needSearch) {
        Disposable disposable = mRepository.searchKanji(needSearch)
//            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<RealmResults<Kanji>>() {
                @Override
                public void onNext(@NonNull RealmResults<Kanji> kanjis) {
                    mViewModel.onSearchKanjiSuccess(kanjis);
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
