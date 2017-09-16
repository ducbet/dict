package com.tmd.dictionary.screen.fragment.viedetail;

import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface VieDetailContract {
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
        void saveToHistory(VieWord vieWord);
        void changeLikeState(VieWord vieWord);
        void isLiked(String key);
    }
}
