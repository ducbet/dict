package com.tmd.dictionary.screen.fragment.level2.kanji;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface KanjiContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onSearchKanjiSuccess(List<Kanji> response);
        void onSearchKanjiFailed();
        void onSetNeedSearch(String needSearch);
        void onClearData();
        void onItemClick(Kanji kanji);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void search(String needSearch);
    }
}
