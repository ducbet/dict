package com.tmd.dictionary.screen.activity.boxs;

import android.os.Parcelable;

import com.tmd.dictionary.data.model.JpnBox;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;
import com.tmd.dictionary.screen.fragment.listboxes.ListBoxesContract;

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
        void onOpenLearningJpnFragment(ListBoxesContract.ViewModel mListBoxesViewModel, JpnBox box);
        void onOpenLearningVieFragment();
        void onOpenLearningKanjiFragment();
        void onOpenLearningGrammarFragment();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
