package com.tmd.dictionary.screen.fragment.history;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.History;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.VieWord;
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
        void onItemClick(JpnWord jpnWord);
        void onItemClick(VieWord vieWord);
        void onItemClick(Grammar grammar);
        void onItemClick(Kanji kanji);
        void onGetHistorySuccess(History history);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getHistory();
    }
}
