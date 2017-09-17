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
        void onSearchEqualSuccess(RealmResults<JpnWord> jpnWords);
        void onSearchLikeSuccess(RealmResults<JpnWord> jpnWords);
        void onSetNeedSearch(String needSearch);
        void onChainingQuery(String needSearch, RealmResults<JpnWord> parentsResult);
        void onRemoveLastResult();
        void onClearRealmResults();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void chaningQueryEqual(String input, RealmResults<JpnWord> parentsResult);
        void chaningQueryLike(String input, RealmResults<JpnWord> parentsResult);
        void searchEqualHasKanjis(String needSearch);
        void searchLikeHasKanjis(String needSearch);
        void searchEqualNotHasKanjis(String needSearch);
        void searchLikeNotHasKanjis(String needSearch);
    }
}
