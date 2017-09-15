package com.tmd.dictionary.screen.fragment.search.level2.viejav;

import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

import io.realm.RealmResults;

/**
 * This specifies the contract between the view and the presenter.
 */
interface VieJavContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onSearchVieJpnSuccess(RealmResults<VieWord> vieWords);
        void onSetNeedSearch(String needSearch);
        void onChainingQuery(String needSearch, RealmResults<VieWord> parentsResult);
        void onRemoveLastResult();
        void onClearRealmResults();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void search(String needSearch);
        void chaningQuery(String input, RealmResults<VieWord> parentsResult);
    }
}
