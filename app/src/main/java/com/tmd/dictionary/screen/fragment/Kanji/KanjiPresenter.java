package com.tmd.dictionary.screen.fragment.Kanji;

import com.tmd.dictionary.data.source.DataSource;

import java.util.List;

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
    public void search(String needSearch) {
        List response = mRepository.searchKanji(needSearch);
        if (response.isEmpty()) {
            mViewModel.onSearchKanjiFailed();
            return;
        }
        mViewModel.onSearchKanjiSuccess(response);
    }
}
