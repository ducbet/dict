package com.tmd.dictionary.screen.fragment.Grammar;

import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.screen.activity.search.SearchContract;

/**
 * Exposes the data to be used in the Grammar screen.
 */
public class GrammarViewModel implements GrammarContract.ViewModel {
    private SearchContract.ViewModel mSearchViewModel;
    private GrammarContract.Presenter mPresenter;
    private String mNeedSearch;
    private GrammarAdapter mAdapter;

    public GrammarViewModel(SearchContract.ViewModel searchViewModel) {
        mSearchViewModel = searchViewModel;
        mAdapter = new GrammarAdapter(this);
    }

    public GrammarAdapter getAdapter() {
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
    public void setPresenter(GrammarContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onSearchGrammarSuccess(Word response) {
        mAdapter.setSource(response);
    }

    @Override
    public void onSearchGrammarFailed() {
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
