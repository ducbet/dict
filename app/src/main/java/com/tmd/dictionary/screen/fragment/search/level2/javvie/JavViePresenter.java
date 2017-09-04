package com.tmd.dictionary.screen.fragment.search.level2.javvie;

import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.source.DataSource;

import io.realm.RealmResults;

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
    public void chaningQuery(String input, RealmResults<JpnWord> parentsResult) {
        mViewModel.onSearchJpnVieSuccess(mRepository.chaningJpnQuery(input, parentsResult));
    }

    @Override
    public void searchJpnWordHasKanjis(String needSearch) {
        mViewModel.onSearchJpnVieSuccess(mRepository.searchJpnWordHasKanjis(needSearch));
    }

    @Override
    public void searchJpnWordNotHasKanjis(String needSearch) {
        mViewModel.onSearchJpnVieSuccess(mRepository.searchJpnWordNotHasKanjis(needSearch));
    }
}
