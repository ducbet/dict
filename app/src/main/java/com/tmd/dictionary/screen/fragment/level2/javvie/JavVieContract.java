package com.tmd.dictionary.screen.fragment.level2.javvie;

import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface JavVieContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onSearchJpnVieSuccess(Word response);
        void onSearchJpnVieFailed();
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