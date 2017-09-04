package com.tmd.dictionary.screen.fragment.search.level2.javvie;

import android.app.Activity;

import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.screen.fragment.search.SearchContract;
import com.tmd.dictionary.screen.fragment.search.SearchViewModel;
import com.tmd.dictionary.staticfinal.SoftKeybroad;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Exposes the data to be used in the JavVie screen.
 */
public class JavVieViewModel implements JavVieContract.ViewModel {
    private SearchContract.ViewModel mSearchViewModel;
    private JavVieContract.Presenter mPresenter;
    private String mNeedSearch;
    private JavVieAdapter mAdapter;
    private RealmChangeListener mRealmChangeListener =
        new RealmChangeListener<RealmResults<JpnWord>>() {
            @Override
            public void onChange(RealmResults<JpnWord> jpnWords) {
                mAdapter.setSource(jpnWords);
            }
        };
    private RealmResults<JpnWord> mJpnWords;

    public JavVieViewModel(SearchContract.ViewModel searchViewModel) {
        mSearchViewModel = searchViewModel;
        mAdapter = new JavVieAdapter(this);
    }

    public JavVieAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        if (mJpnWords != null) {
            mJpnWords.removeAllChangeListeners();
        }
    }

    @Override
    public void setPresenter(JavVieContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onSearchJpnVieSuccess(RealmResults<JpnWord> jpnWords) {
        jpnWords.addChangeListener(mRealmChangeListener);
        mJpnWords = jpnWords;
    }

    @Override
    public void onSearchJpnVieFailed() {
    }

    @Override
    public void onSetNeedSearch(String needSearch) {
        mNeedSearch = needSearch;
        mPresenter.search(needSearch);
    }

    @Override
    public void onClearData() {
        mAdapter.clearData();
    }

    @Override
    public void onItemClick(JpnWord jpnWord) {
        SoftKeybroad.hide((Activity) ((SearchViewModel) mSearchViewModel).getContext());
        mSearchViewModel.onItemClick(jpnWord);
    }
}
