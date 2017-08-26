package com.tmd.dictionary.screen.fragment.search.level2.grammar;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.screen.fragment.search.SearchContract;

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
    public void onSearchGrammarSuccess(Grammar grammar) {
        mAdapter.setSource(grammar);
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
    public void onItemClick(Grammar grammar) {
        mSearchViewModel.onItemClick(grammar);
    }
}
