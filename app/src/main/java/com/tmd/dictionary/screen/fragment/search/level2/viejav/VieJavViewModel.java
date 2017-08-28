package com.tmd.dictionary.screen.fragment.search.level2.viejav;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.screen.fragment.search.SearchContract;
import com.tmd.dictionary.util.DictApplication;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Exposes the data to be used in the VieJav screen.
 */
public class VieJavViewModel implements VieJavContract.ViewModel {
    private SearchContract.ViewModel mSearchViewModel;
    private VieJavContract.Presenter mPresenter;
    private String mNeedSearch;
    private VieJpnAdapter mAdapter;
    private Realm mRealm;

    public VieJavViewModel(SearchContract.ViewModel searchViewModel) {
        mSearchViewModel = searchViewModel;
        mAdapter = new VieJpnAdapter(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
            .assetFile(DictApplication.getContext().getString(R.string.database_name))
            .build();
        mRealm = Realm.getInstance(config);
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
        mSearchViewModel.onItemClick(vieWord);
    }
}
