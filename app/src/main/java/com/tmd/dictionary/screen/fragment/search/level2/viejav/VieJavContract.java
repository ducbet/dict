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
        void onSearchVieJpnFailed();
        void onSetNeedSearch(String needSearch);
        void onClearRealmResults();
        void onItemClick(VieWord word);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void search(String needSearch);
    }
}
