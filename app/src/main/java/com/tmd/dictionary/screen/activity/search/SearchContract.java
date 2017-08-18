package com.tmd.dictionary.screen.activity.search;

import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

import java.io.Serializable;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface SearchContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter>, Serializable {
        void onSendToAllFragment(String needSearch);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
