package com.tmd.dictionary.screen.fragment.history;

import com.tmd.dictionary.data.model.History;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface HistoryContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetHistorySuccess(History history);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getHistory();
    }
}
