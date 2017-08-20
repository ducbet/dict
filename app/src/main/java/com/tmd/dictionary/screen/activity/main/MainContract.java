package com.tmd.dictionary.screen.activity.main;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

import java.io.Serializable;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface MainContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter>, Serializable {
        boolean isSearchFragVisibility();
        void onOpenKanjiDetailFragment(Kanji kanji);
        void onOpenJpnWordDetailFragment(Word word);
        void onOpenVieWordDetailFragment(Word word);
        void onOpenGrammarDetailFragment(Word word);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
