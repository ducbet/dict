package com.tmd.dictionary.screen.fragment.kanjidetail;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface KanjiDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onChangeLikeState();
        void onSetLiked(Boolean isLiked);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void saveToHistory(Kanji kanji);
        void changeLikeState(Kanji kanji);
        void isLiked(String key);
    }
}
