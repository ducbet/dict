package com.tmd.dictionary.screen.fragment.search;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

import java.io.Serializable;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface SearchContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter>, Serializable {
        void onSendToAllFragment(String needSearch);
        void onItemClick(BaseViewModel viewModel, Word word);
        void onItemClick(Kanji kanji);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
