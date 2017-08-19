package com.tmd.dictionary.screen.fragment.VieJav;

import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface VieJavContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onSearchVieJpnSuccess(Word response);
        void onSearchVieJpnFailed();
        void onSetNeedSearch(String needSearch);
        void onClearData();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void search(String needSearch);
    }
}
