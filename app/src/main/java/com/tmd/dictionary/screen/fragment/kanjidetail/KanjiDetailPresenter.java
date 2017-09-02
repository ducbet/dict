package com.tmd.dictionary.screen.fragment.kanjidetail;

import com.tmd.dictionary.data.source.DataSource;

import io.realm.Realm;

import static com.tmd.dictionary.staticfinal.ConstantValue.INT_KANJI;

/**
 * Listens to user actions from the UI ({@link KanjiDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
final class KanjiDetailPresenter implements KanjiDetailContract.Presenter {
    private static final String TAG = KanjiDetailPresenter.class.getName();
    private final KanjiDetailContract.ViewModel mViewModel;
    private DataSource mRepository;

    public KanjiDetailPresenter(KanjiDetailContract.ViewModel viewModel, DataSource repository) {
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
    public void saveToHistory(Realm realm, String primaryKey) {
        mRepository.saveToHistory(realm, INT_KANJI, primaryKey);
    }
}
