package com.tmd.dictionary.screen.fragment.grammardetail;

import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

import io.realm.Realm;

/**
 * This specifies the contract between the view and the presenter.
 */
interface GrammarDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void saveToHistory(Realm realm, String primaryKey);
    }
}
