package com.tmd.dictionary.screen.fragment.search.level2.grammar;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface GrammarContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onSearchGrammarSuccess(Grammar grammar);
        void onSearchGrammarFailed();
        void onSetNeedSearch(String needSearch);
        void onClearData();
        void onItemClick(Grammar grammar);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void search(String needSearch);
    }
}
