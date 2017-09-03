package com.tmd.dictionary.screen.fragment.search.level2.viejav;

import android.app.Activity;

import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.screen.fragment.search.SearchContract;
import com.tmd.dictionary.screen.fragment.search.SearchViewModel;
import com.tmd.dictionary.staticfinal.SoftKeybroad;

import io.realm.RealmResults;

/**
 * Exposes the data to be used in the VieJav screen.
 */
public class VieJavViewModel implements VieJavContract.ViewModel {
    private SearchContract.ViewModel mSearchViewModel;
    private VieJavContract.Presenter mPresenter;
    private String mNeedSearch;
    private VieJpnAdapter mAdapter;

    public VieJavViewModel(SearchContract.ViewModel searchViewModel) {
        mSearchViewModel = searchViewModel;
        mAdapter = new VieJpnAdapter(this);
    }

    public VieJpnAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(VieJavContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onSearchVieJpnSuccess(RealmResults<VieWord> vieWords) {
        mAdapter.setSource(vieWords);
    }

    @Override
    public void onSearchVieJpnFailed() {
    }

    @Override
    public void onSetNeedSearch(String needSearch) {
        mNeedSearch = needSearch;
        mPresenter.search(needSearch);
    }

    @Override
    public void onClearData() {
        mAdapter.clearData();
    }

    @Override
    public void onItemClick(VieWord vieWord) {
        SoftKeybroad.hide((Activity) ((SearchViewModel) mSearchViewModel).getContext());
        mSearchViewModel.onItemClick(vieWord);
    }
}
