package com.tmd.dictionary.screen.fragment.jpndetail;

import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.source.DataSource;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Listens to user actions from the UI ({@link JpnDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
final class JpnDetailPresenter implements JpnDetailContract.Presenter {
    private static final String TAG = JpnDetailPresenter.class.getName();
    private final JpnDetailContract.ViewModel mViewModel;
    private DataSource mRepository;
    private CompositeDisposable mCompositeDisposable;

    public JpnDetailPresenter(JpnDetailContract.ViewModel viewModel, DataSource repository) {
        mViewModel = viewModel;
        mRepository = repository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
        mRepository.closeDatabase();
    }

    @Override
    public void searchKanjis(JpnWord jpnWord) {
        mViewModel.onClearKanjisData();
        Disposable disposable = mRepository.searchKanji(jpnWord.getOrigin())
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<List<Kanji>>() {
                @Override
                public void onNext(@NonNull List<Kanji> kanjis) {
                    mViewModel.onSearchKanjisSuccess(kanjis);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.onSearchKanjisFailed();
                }

                @Override
                public void onComplete() {
                }
            });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void searchExamples(JpnWord jpnWord) {
        mViewModel.onClearExamplesData();
//        Disposable disposable = mRepository.searchExamplesOfWord(jpnWord.getId())
        Disposable disposable = mRepository.searchExamplesOfWord(0)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<List<String>>() {
                @Override
                public void onNext(@NonNull List<String> examples) {
                    mViewModel.onSearchExamplesSuccess(examples);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.onSearchExamplesFailed();
                }

                @Override
                public void onComplete() {
                }
            });
        mCompositeDisposable.add(disposable);
    }
}
