package com.tmd.dictionary.screen.fragment.jpndetail;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.screen.BasePresenter;
import com.tmd.dictionary.screen.BaseViewModel;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface JpnDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onSearchKanjisSuccess(List<Kanji> kanjis);
        void onSearchKanjisFailed();
        void onSearchExamplesSuccess(List<String> examples);
        void onSearchExamplesFailed();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void searchKanjis(Word word);
        void searchExamples(Word word);
    }
}
