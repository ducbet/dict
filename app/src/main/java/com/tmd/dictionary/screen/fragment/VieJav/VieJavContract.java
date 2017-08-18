package com.tmd.dictionary.screen.fragment.VieJav;

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
        void onSetNeedSearch(String needSearch);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void search(String needSearch);
    }
}
