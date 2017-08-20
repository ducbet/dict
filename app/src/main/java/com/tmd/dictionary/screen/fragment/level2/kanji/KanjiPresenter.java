package com.tmd.dictionary.screen.fragment.level2.kanji;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.source.DataSource;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Listens to user actions from the UI ({@link KanjiFragment}), retrieves the data and updates
 * the UI as required.
 */
final class KanjiPresenter implements KanjiContract.Presenter {
    private static final String TAG = KanjiPresenter.class.getName();
    private final KanjiContract.ViewModel mViewModel;
    private DataSource mRepository;

    public KanjiPresenter(KanjiContract.ViewModel viewModel, DataSource repository) {
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
        mRepository.searchKanji(needSearch)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new Observer<List<Kanji>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mViewModel.onClearData();
                }

                @Override
                public void onNext(@NonNull List<Kanji> kanji) {
                    mViewModel.onSearchKanjiSuccess(kanji);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                }

                @Override
                public void onComplete() {
                }
            });
    }
}
