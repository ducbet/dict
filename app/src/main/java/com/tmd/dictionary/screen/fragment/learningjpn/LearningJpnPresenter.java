package com.tmd.dictionary.screen.fragment.learningjpn;

import com.tmd.dictionary.data.source.DataSource;
import com.tmd.dictionary.data.source.Repository;

/**
 * Listens to user actions from the UI ({@link LearningJpnFragment}), retrieves the data and updates
 * the UI as required.
 */
final class LearningJpnPresenter implements LearningJpnContract.Presenter {
    private static final String TAG = LearningJpnPresenter.class.getName();
    private final LearningJpnContract.ViewModel mViewModel;
    private DataSource mRepository;

    public LearningJpnPresenter(LearningJpnContract.ViewModel viewModel, Repository repository) {
        mViewModel = viewModel;
        mRepository = repository;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
