package com.tmd.dictionary.screen.fragment.search.level2.viejav;

import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.data.source.DataSource;

import io.realm.RealmResults;

/**
 * Listens to user actions from the UI ({@link VieJavFragment}), retrieves the data and updates
 * the UI as required.
 */
final class VieJavPresenter implements VieJavContract.Presenter {
    private static final String TAG = VieJavPresenter.class.getName();
    private final VieJavContract.ViewModel mViewModel;
    private DataSource mRepository;

    public VieJavPresenter(VieJavContract.ViewModel viewModel, DataSource repository) {
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
        mViewModel.onSearchVieJpnSuccess(mRepository.searchVieJpn(needSearch));
    }

    @Override
    public void chaningQuery(String input, RealmResults<VieWord> parentsResult) {
        mViewModel.onSearchVieJpnSuccess(mRepository.chaningVieQuery(input, parentsResult));
    }
}
