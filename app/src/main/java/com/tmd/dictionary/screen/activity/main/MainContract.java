package com.tmd.dictionary.screen.activity.main;

import android.os.Parcelable;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;
import com.tmd.dictionary.screen.OpenableMoveToBoxFrag;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface MainContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter>, Parcelable {
        boolean isSearchFragVisibility();
        void onOpenKanjiDetailFragment(Kanji kanji);
        void onOpenJpnWordDetailFragment(JpnWord jpnWord);
        void onOpenVieWordDetailFragment(VieWord vieWord);
        void onOpenGrammarDetailFragment(Grammar grammar);
        void onOpenSearchFragment();
        void onOpenSearchFragment(String needSearch);
        void onOpenHistoryFragment();
        void onOpenLikedFragment();
        void onOpenMoveToBoxFragment(OpenableMoveToBoxFrag viewModel);
        void onOpenBoxesActivity();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void createHistoryObjectIfNotExist();
        void createLikedWordObjectIfNotExist();
    }
}
