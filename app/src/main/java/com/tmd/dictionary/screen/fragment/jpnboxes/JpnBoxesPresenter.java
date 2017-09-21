package com.tmd.dictionary.screen.fragment.jpnboxes;

import com.tmd.dictionary.data.source.DataSource;
import com.tmd.dictionary.data.source.Repository;

/**
 * Listens to user actions from the UI ({@link JpnBoxesFragment}), retrieves the data and updates
 * the UI as required.
 */
final class JpnBoxesPresenter implements JpnBoxesContract.Presenter {
    private static final String TAG = JpnBoxesPresenter.class.getName();
    private final JpnBoxesContract.ViewModel mViewModel;
    private DataSource mRepository;

    public JpnBoxesPresenter(JpnBoxesContract.ViewModel viewModel, Repository repository) {
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
