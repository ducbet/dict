package com.tmd.dictionary.screen.activity.history;

import com.tmd.dictionary.data.model.History;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface HistoryContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetHistorySuccess(History history);
        void onGetHistoryFailed(Throwable e);
        void onItemClick(Integer type, String key);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getHistory();
    }
}
