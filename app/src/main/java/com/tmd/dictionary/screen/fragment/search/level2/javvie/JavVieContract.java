package com.tmd.dictionary.screen.fragment.search.level2.javvie;

import com.tmd.dictionary.data.model.JpnWord;
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
        void onSearchJpnVieSuccess(JpnWord jpnWord);
        void onSearchJpnVieFailed();
        void onSetNeedSearch(String needSearch);
        void onClearData();
        void onItemClick(JpnWord jpnWord);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void search(String needSearch);
    }
}
