package com.tmd.dictionary.screen.fragment.grammardetail;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.screen.activity.main.MainContract;

/**
 * Exposes the data to be used in the GrammarDetail screen.
 */
public class GrammarDetailViewModel implements GrammarDetailContract.ViewModel {
    private MainContract.ViewModel mMainViewModel;
    private GrammarDetailContract.Presenter mPresenter;
    private Grammar mGrammar;

    public GrammarDetailViewModel(MainContract.ViewModel mainViewModel, Grammar grammar) {
        mMainViewModel = mainViewModel;
        mGrammar = grammar;
    }

    public Grammar getGrammar() {
        return mGrammar;
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
    public void setPresenter(GrammarDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
