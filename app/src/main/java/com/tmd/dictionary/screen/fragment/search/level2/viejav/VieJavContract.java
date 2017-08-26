package com.tmd.dictionary.screen.fragment.search.level2.viejav;

import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface VieJavContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onSearchVieJpnSuccess(VieWord word);
        void onSearchVieJpnFailed();
        void onSetNeedSearch(String needSearch);
        void onClearData();
        void onItemClick(VieWord word);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void search(String needSearch);
    }
}
