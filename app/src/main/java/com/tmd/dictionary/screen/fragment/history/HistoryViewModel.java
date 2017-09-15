package com.tmd.dictionary.screen.fragment.history;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.History;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.screen.OnClickSearchedItemListener;
import com.tmd.dictionary.screen.activity.main.MainContract;

import io.realm.RealmChangeListener;

/**
 * Exposes the data to be used in the History screen.
 */
public class HistoryViewModel implements HistoryContract.ViewModel, OnClickSearchedItemListener {
    private MainContract.ViewModel mMainViewModel;
    private HistoryContract.Presenter mPresenter;
    private HistoryAdapter mAdapter;
    private RealmChangeListener mRealmChangeListener =
        new RealmChangeListener<History>() {
            @Override
            public void onChange(History history) {
                mAdapter.setSource(history);
            }
        };
    private History mHistory;

    public HistoryViewModel(MainContract.ViewModel mainViewModel) {
        mMainViewModel = mainViewModel;
        mAdapter = new HistoryAdapter(this);
    }

    public HistoryAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getHistory();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        if (mHistory.isValid()) {
            mHistory.removeAllChangeListeners();
        }
    }

    @Override
    public void setPresenter(HistoryContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemClick(JpnWord jpnWord) {
        mMainViewModel.onOpenJpnWordDetailFragment(jpnWord);
    }

    @Override
    public void onItemClick(VieWord vieWord) {
        mMainViewModel.onOpenVieWordDetailFragment(vieWord);
    }

    @Override
    public void onItemClick(Grammar grammar) {
        mMainViewModel.onOpenGrammarDetailFragment(grammar);
    }

    @Override
    public void onItemClick(Kanji kanji) {
        mMainViewModel.onOpenKanjiDetailFragment(kanji);
    }

    @Override
    public void onGetHistorySuccess(History history) {
        mHistory = history;
        mHistory.addChangeListener(mRealmChangeListener);
    }

    @Override
    public void onClick(Object item) {
        if (item instanceof JpnWord) {
            onItemClick((JpnWord) item);
            return;
        }
        if (item instanceof VieWord) {
            onItemClick((VieWord) item);
            return;
        }
        if (item instanceof Kanji) {
            onItemClick((Kanji) item);
            return;
        }
        if (item instanceof Grammar) {
            onItemClick((Grammar) item);
            return;
        }
    }
}
