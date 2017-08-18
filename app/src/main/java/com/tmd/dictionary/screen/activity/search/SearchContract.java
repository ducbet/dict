package com.tmd.dictionary.screen.activity.search;

import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

import java.io.Serializable;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface SearchContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter>, Serializable {
        void onSearch_1Success();
        void onSearch_1Failed();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void JpnVieDefinition(String needSearch);
    }
}
