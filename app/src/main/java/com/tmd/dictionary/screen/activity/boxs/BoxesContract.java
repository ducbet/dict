package com.tmd.dictionary.screen.activity.boxs;

import android.os.Parcelable;

import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface BoxesContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter>, Parcelable {
        void onOpenJpnBoxesFragment();
        void onOpenVieBoxesFragment();
        void onOpenKanjiBoxesFragment();
        void onOpenGrammarBoxesFragment();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
