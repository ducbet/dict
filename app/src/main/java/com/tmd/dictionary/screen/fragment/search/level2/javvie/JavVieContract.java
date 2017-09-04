package com.tmd.dictionary.screen.fragment.search.level2.javvie;

import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

import io.realm.RealmResults;

/**
 * This specifies the contract between the view and the presenter.
 */
interface JavVieContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onSearchJpnVieSuccess(RealmResults<JpnWord> jpnWord);
        void onSetNeedSearch(String needSearch);
        void onChainingQuery(String needSearch, RealmResults<JpnWord> parentsResult);
        void onRemoveLastResult();
        void onClearRealmResults();
        void onItemClick(JpnWord jpnWord);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void chaningQuery(String input, RealmResults<JpnWord> parentsResult);
        void searchJpnWordHasKanjis(String needSearch);
        void searchJpnWordNotHasKanjis(String needSearch);
    }
}
