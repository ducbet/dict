package com.tmd.dictionary.screen.fragment.search.level2.javvie;

import com.tmd.dictionary.data.source.DataSource;

/**
 * Listens to user actions from the UI ({@link JavVieFragment}), retrieves the data and updates
 * the UI as required.
 */
final class JavViePresenter implements JavVieContract.Presenter {
    private static final String TAG = JavViePresenter.class.getName();
    private final JavVieContract.ViewModel mViewModel;
    private DataSource mRepository;

    public JavViePresenter(JavVieContract.ViewModel viewModel, DataSource repository) {
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
        mViewModel.onSearchJpnVieSuccess(mRepository.searchJpnVie(needSearch));
    }
}
