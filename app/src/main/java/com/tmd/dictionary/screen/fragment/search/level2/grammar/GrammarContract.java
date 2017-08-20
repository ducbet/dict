package com.tmd.dictionary.screen.fragment.search.level2.grammar;

import com.tmd.dictionary.data.model.Word;
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
        void onSearchGrammarSuccess(Word response);
        void onSearchGrammarFailed();
        void onSetNeedSearch(String needSearch);
        void onClearData();
        void onItemClick(Word word);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void search(String needSearch);
    }
}
