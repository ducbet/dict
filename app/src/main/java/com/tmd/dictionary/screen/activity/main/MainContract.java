package com.tmd.dictionary.screen.activity.main;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.VieWord;
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
        void onOpenJpnWordDetailFragment(JpnWord jpnWord);
        void onOpenVieWordDetailFragment(VieWord vieWord);
        void onOpenGrammarDetailFragment(Grammar grammar);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
