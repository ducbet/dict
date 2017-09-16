package com.tmd.dictionary.screen.fragment.likedwords;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.LikedWord;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.screen.OnClickSearchedItemListener;
import com.tmd.dictionary.screen.activity.main.MainContract;

import io.realm.RealmChangeListener;

/**
 * Exposes the data to be used in the Liked screen.
 */
public class LikedWordsViewModel
    implements LikedWordsContract.ViewModel, OnClickSearchedItemListener {
    private MainContract.ViewModel mMainViewModel;
    private LikedWordsContract.Presenter mPresenter;
    private LikedWordsAdapter mAdapter;
    private RealmChangeListener mRealmChangeListener =
        new RealmChangeListener<LikedWord>() {
            @Override
            public void onChange(LikedWord likedWords) {
                mAdapter.setSource(likedWords);
            }
        };
    private LikedWord mLikedWords;

    public LikedWordsViewModel(MainContract.ViewModel mainViewModel) {
        mMainViewModel = mainViewModel;
        mAdapter = new LikedWordsAdapter(this);
    }

    public LikedWordsAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getLikedWords();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        if (mLikedWords.isValid()) {
            mLikedWords.removeAllChangeListeners();
        }
    }

    @Override
    public void setPresenter(LikedWordsContract.Presenter presenter) {
        mPresenter = presenter;
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
    public void onGetLikedWordsSuccess(LikedWord likedWords) {
        mLikedWords = likedWords;
        mLikedWords.addChangeListener(mRealmChangeListener);
    }
}
