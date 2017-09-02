package com.tmd.dictionary.screen.fragment.grammardetail;

import com.tmd.dictionary.data.source.DataSource;

import io.realm.Realm;

import static com.tmd.dictionary.staticfinal.ConstantValue.INT_GRAMMAR;

/**
 * Listens to user actions from the UI ({@link GrammarDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
final class GrammarDetailPresenter implements GrammarDetailContract.Presenter {
    private static final String TAG = GrammarDetailPresenter.class.getName();
    private final GrammarDetailContract.ViewModel mViewModel;
    private DataSource mRepository;

    public GrammarDetailPresenter(GrammarDetailContract.ViewModel viewModel,
                                  DataSource repository) {
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
        mRepository.saveToHistory(realm, INT_GRAMMAR, primaryKey);
    }
}
