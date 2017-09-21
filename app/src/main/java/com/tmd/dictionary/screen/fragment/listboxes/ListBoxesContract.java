package com.tmd.dictionary.screen.fragment.listboxes;

import com.tmd.dictionary.data.model.GrammarBox;
import com.tmd.dictionary.data.model.JpnBox;
import com.tmd.dictionary.data.model.KanjiBox;
import com.tmd.dictionary.data.model.VieBox;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

import io.realm.RealmResults;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ListBoxesContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        RealmResults<JpnBox> getAllJpnBoxes();
        RealmResults<VieBox> getAllVieBoxes();
        RealmResults<KanjiBox> getAllKanjiBoxes();
        RealmResults<GrammarBox> getAllGrammarBoxes();
    }
}
