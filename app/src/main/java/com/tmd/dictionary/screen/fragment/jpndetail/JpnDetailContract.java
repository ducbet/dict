package com.tmd.dictionary.screen.fragment.jpndetail;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface JpnDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onClickKanji(Kanji kanji);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
