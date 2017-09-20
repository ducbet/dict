package com.tmd.dictionary.screen.fragment.grammardetail;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.GrammarBox;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

import io.realm.RealmResults;

/**
 * This specifies the contract between the view and the presenter.
 */
interface GrammarDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onChangeLikeState();
        void onSetLiked(Boolean isLiked);
        void onMoveToBox();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void saveToHistory(Grammar grammar);
        void changeLikeState(Grammar grammar);
        void isLiked(Grammar grammar);
        RealmResults<GrammarBox> getAllFlashcardBoxes();
        void createFlashcardBox(GrammarBox newBox);
    }
}
