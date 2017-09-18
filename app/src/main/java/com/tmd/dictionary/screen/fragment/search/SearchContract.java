package com.tmd.dictionary.screen.fragment.search;

import android.os.Parcelable;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

import io.reactivex.Observable;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface SearchContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter>, Parcelable {
        void onSendToAllFragment(Observable<String> textChangeObservable);
        void onSend(String needSearch);
        void onItemClick(JpnWord jpnWord);
        void onItemClick(VieWord vieWord);
        void onItemClick(Grammar grammar);
        void onItemClick(Kanji kanji);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
