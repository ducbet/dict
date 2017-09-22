package com.tmd.dictionary.screen.fragment.kanjidetail;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.KanjiBox;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

import io.realm.RealmResults;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface KanjiDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onChangeLikeState();
        void onSetLiked(Boolean isLiked);
        void onOpenMoveToBoxFragment();
        void onMoveToBox(KanjiBox kanjiBox, Kanji kanji);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void saveToHistory(Kanji kanji);
        void changeLikeState(Kanji kanji);
        void isLiked(Kanji kanji);
        RealmResults<KanjiBox> getAllFlashcardBoxes();
        void createFlashcardBox(KanjiBox newBox);
        void moveToBox(KanjiBox kanjiBox, Kanji kanji);
    }
}
