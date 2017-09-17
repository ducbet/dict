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
    public void chaningQueryEqual(String input, RealmResults<JpnWord> parentsResult) {
        mViewModel.onSearchEqualSuccess(mRepository.chaningJpnQueryEqual(input, parentsResult));
    }

    @Override
    public void chaningQueryLike(String input, RealmResults<JpnWord> parentsResult) {
        mViewModel.onSearchLikeSuccess(mRepository.chaningJpnQueryLike(input, parentsResult));
    }

    @Override
    public void searchEqualHasKanjis(String needSearch) {
        mViewModel.onSearchEqualSuccess(mRepository.searchEqualJpnWordHasKanjis(needSearch));
    }

    @Override
    public void searchLikeHasKanjis(String needSearch) {
        mViewModel.onSearchLikeSuccess(mRepository.searchLikeJpnWordHasKanjis(needSearch));
    }

    @Override
    public void searchEqualNotHasKanjis(String needSearch) {
        mViewModel.onSearchEqualSuccess(mRepository.searchEqualJpnWordNotHasKanjis(needSearch));
    }

    @Override
    public void searchLikeNotHasKanjis(String needSearch) {
        mViewModel.onSearchLikeSuccess(mRepository.searchLikeJpnWordNotHasKanjis(needSearch));
    }
}
