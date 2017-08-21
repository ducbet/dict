package com.tmd.dictionary.screen.fragment.jpndetail;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.data.source.DataSource;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Listens to user actions from the UI ({@link JpnDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
final class JpnDetailPresenter implements JpnDetailContract.Presenter {
    private static final String TAG = JpnDetailPresenter.class.getName();
    private final JpnDetailContract.ViewModel mViewModel;
    private DataSource mRepository;

    public JpnDetailPresenter(JpnDetailContract.ViewModel viewModel, DataSource repository) {
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
    public void searchKanjis(Word word) {
        mRepository.searchKanji(word.getOrigin())
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new Observer<List<Kanji>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                }

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
    }

    @Override
    public void searchExamples(Word word) {
        mRepository.searchExamplesOfWord(word.getId())
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new Observer<List<String>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                }

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
    }
}
