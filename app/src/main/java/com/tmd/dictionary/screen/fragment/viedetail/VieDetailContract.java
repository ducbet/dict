package com.tmd.dictionary.screen.fragment.viedetail;

import com.tmd.dictionary.data.model.VieBox;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

import io.realm.RealmResults;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface VieDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onChangeLikeState();
        void onSetLiked(Boolean isLiked);
        void onOpenMoveToBoxFragment();
        void onMoveToBox(VieBox vieBox, VieWord vieWord);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void saveToHistory(VieWord vieWord);
        void changeLikeState(VieWord vieWord);
        void isLiked(VieWord vieWord);
        RealmResults<VieBox> getAllFlashcardBoxes();
        void createFlashcardBox(VieBox newBox);
        void moveToBox(VieBox vieBox, VieWord vieWord);
    }
}
