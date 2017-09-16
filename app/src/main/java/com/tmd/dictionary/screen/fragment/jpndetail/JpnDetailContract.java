package com.tmd.dictionary.screen.fragment.jpndetail;

import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface JpnDetailContract {
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
        void saveToHistory(JpnWord jpnWord);
        void changeLikeState(JpnWord jpnWord);
        void isLiked(String key);
    }
}
