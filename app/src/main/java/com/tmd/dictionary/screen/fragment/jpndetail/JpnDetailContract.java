package com.tmd.dictionary.screen.fragment.jpndetail;

import android.os.Parcelable;

import com.tmd.dictionary.data.model.JpnBox;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

import io.realm.RealmResults;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface JpnDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter>, Parcelable {
        void onChangeLikeState();
        void onSetLiked(Boolean isLiked);
        void onMoveToBox();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void saveToHistory(JpnWord jpnWord);
        void changeLikeState(JpnWord jpnWord);
        void isLiked(JpnWord jpnWord);
        RealmResults<JpnBox> getAllFlashcardBoxes();
        void createFlashcardBox(JpnBox newBox);
    }
}
