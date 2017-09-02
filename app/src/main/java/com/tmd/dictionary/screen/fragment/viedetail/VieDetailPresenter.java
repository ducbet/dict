package com.tmd.dictionary.screen.fragment.viedetail;

import com.tmd.dictionary.data.source.DataSource;

import io.realm.Realm;

import static com.tmd.dictionary.staticfinal.ConstantValue.INT_VIE_WORD;

/**
 * Listens to user actions from the UI ({@link VieDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
final class VieDetailPresenter implements VieDetailContract.Presenter {
    private static final String TAG = VieDetailPresenter.class.getName();
    private final VieDetailContract.ViewModel mViewModel;
    private DataSource mRepository;

    public VieDetailPresenter(VieDetailContract.ViewModel viewModel, DataSource repository) {
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
        mRepository.saveToHistory(realm, INT_VIE_WORD, primaryKey);
    }
}
