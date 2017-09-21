package com.tmd.dictionary.screen.activity.boxs;

import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface BoxesContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onJpnBoxesClicked();
        void onVieBoxesClicked();
        void onKanjiBoxesClicked();
        void onGrammarBoxesClicked();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
