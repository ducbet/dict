package com.tmd.dictionary.screen.fragment.search.level2.javvie;

import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.screen.fragment.search.SearchContract;

/**
 * Exposes the data to be used in the JavVie screen.
 */
public class JavVieViewModel implements JavVieContract.ViewModel {
    private SearchContract.ViewModel mSearchViewModel;
    private JavVieContract.Presenter mPresenter;
    private String mNeedSearch;
    private JavVieAdapter mAdapter;

    public JavVieViewModel(SearchContract.ViewModel searchViewModel) {
        mSearchViewModel = searchViewModel;
        mAdapter = new JavVieAdapter(this);
    }

    public JavVieAdapter getAdapter() {
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
    public void setPresenter(JavVieContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onSearchJpnVieSuccess(Word response) {
        mAdapter.setSource(response);
    }

    @Override
    public void onSearchJpnVieFailed() {
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
    public void onItemClick(Word word) {
    }
}
