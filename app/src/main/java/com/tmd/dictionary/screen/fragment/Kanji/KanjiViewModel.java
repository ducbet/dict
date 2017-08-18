package com.tmd.dictionary.screen.fragment.Kanji;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.screen.activity.search.SearchContract;

import java.util.List;

/**
 * Exposes the data to be used in the Kanji screen.
 */
public class KanjiViewModel implements KanjiContract.ViewModel {
    private SearchContract.ViewModel mSearchViewModel;
    private KanjiContract.Presenter mPresenter;
    private String mNeedSearch;

    public KanjiViewModel(SearchContract.ViewModel searchViewModel) {
        mSearchViewModel = searchViewModel;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(KanjiContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onSearchKanjiSuccess(List<Kanji> response) {
    }

    @Override
    public void onSearchKanjiFailed() {
    }

    @Override
    public void onSetNeedSearch(String needSearch) {
        mNeedSearch = needSearch;
        mPresenter.search(needSearch);
    }
}
