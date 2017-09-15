package com.tmd.dictionary.screen.fragment.search.level2.grammar;

import android.app.Activity;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.screen.OnClickSearchedItemListener;
import com.tmd.dictionary.screen.fragment.search.SearchContract;
import com.tmd.dictionary.screen.fragment.search.SearchViewModel;
import com.tmd.dictionary.staticfinal.SoftKeybroad;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Exposes the data to be used in the Grammar screen.
 */
public class GrammarViewModel
    implements GrammarContract.ViewModel, OnClickSearchedItemListener<Grammar> {
    private SearchContract.ViewModel mSearchViewModel;
    private GrammarContract.Presenter mPresenter;
    private String mNeedSearch;
    private GrammarAdapter mAdapter;
    private Realm mRealm;

    public GrammarViewModel(Realm realm, SearchContract.ViewModel searchViewModel) {
        mRealm = realm;
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
    public void onSearchGrammarSuccess(RealmResults<Grammar> grammars) {
        mAdapter.setSource(grammars);
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
    public void onClearRealmResults() {
    }

    @Override
    public void onClick(Grammar grammar) {
        SoftKeybroad.hide((Activity) ((SearchViewModel) mSearchViewModel).getContext());
        mSearchViewModel.onItemClick(mRealm.copyFromRealm(grammar));
    }
}
