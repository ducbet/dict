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
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
