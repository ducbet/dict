package com.tmd.dictionary.screen.fragment.search.level2.javvie;

import android.app.Activity;
import android.util.Log;

import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.screen.OnClickSearchedItemListener;
import com.tmd.dictionary.screen.fragment.search.SearchContract;
import com.tmd.dictionary.screen.fragment.search.SearchViewModel;
import com.tmd.dictionary.staticfinal.SoftKeybroad;
import com.tmd.dictionary.staticfinal.StringHandling;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

import static com.tmd.dictionary.staticfinal.ConstantValue.MY_TAG;

/**
 * Exposes the data to be used in the JavVie screen.
 */
public class JavVieViewModel
    implements JavVieContract.ViewModel, OnClickSearchedItemListener<JpnWord> {
    private static final int CHAINING_QUERY_1 = 1;
    private static final int CHAINING_QUERY_2 = 2;
    private static final int SEARCH_HAS_KANJI = 3;
    private static final int SEARCH_NOT_HAS_KANJI = 4;
    private int mSearchType;
    private boolean mIsQueryLike;
    private SearchContract.ViewModel mSearchViewModel;
    private JavVieContract.Presenter mPresenter;
    private String mNeedSearch = "";
    private JavVieAdapter mAdapter;
    private RealmChangeListener<RealmResults<JpnWord>> mRealmChangeListener =
        new RealmChangeListener<RealmResults<JpnWord>>() {
            @Override
            public void onChange(RealmResults<JpnWord> jpnWords) {
                mAdapter.setSource(jpnWords);
                if (!mIsQueryLike) {
                    mIsQueryLike = true;
                    switch (mSearchType) {
                        case CHAINING_QUERY_1:
                            mPresenter.chaningQueryLike(mNeedSearch,
                                mListResults.get(mListResults.size() - 1));
                            break;
                        case CHAINING_QUERY_2:
                            mPresenter
                                .chaningQueryLike(mNeedSearch,
                                    mListResults.get(mNeedSearch.length() - 1));
                            break;
                        case SEARCH_HAS_KANJI:
                            mPresenter.searchLikeHasKanjis(mNeedSearch);
                            break;
                        case SEARCH_NOT_HAS_KANJI:
//                            mPresenter.searchLikeNotHasKanjis(mNeedSearch);
                            Log.e(MY_TAG, "SEARCH_NOT_HAS_KANJI: ");
                            RealmResults<JpnWord> jpnWords1 =
                                mRealm.where(JpnWord.class).findAllAsync();
                            jpnWords1.addChangeListener(this);
                            break;
                        default:
                            break;
                    }
                }
                if (jpnWords.isEmpty()) {
                    onRemoveLastResult();
                }
            }
        };
    private List<RealmResults<JpnWord>> mListResults;
    private Realm mRealm;

    public JavVieViewModel(Realm realm, SearchContract.ViewModel searchViewModel) {
        mRealm = realm;
        mSearchViewModel = searchViewModel;
        mAdapter = new JavVieAdapter(this);
        mListResults = new ArrayList<>();
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
        if (mListResults != null) {
            for (RealmResults<JpnWord> jpnWords : mListResults) {
                jpnWords.removeAllChangeListeners();
            }
        }
    }

    @Override
    public void setPresenter(JavVieContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onSearchEqualSuccess(RealmResults<JpnWord> jpnWords) {
        jpnWords.addChangeListener(mRealmChangeListener);
        mListResults.add(jpnWords);
    }

    private RealmResults<JpnWord> jpnWordsLike;

    @Override
    public void onSearchLikeSuccess(RealmResults<JpnWord> jpnWords) {
        jpnWordsLike = jpnWords;
        Log.e(MY_TAG, "onSearchLikeSuccess: " + Thread.currentThread().getName());
        jpnWordsLike.addChangeListener(mRealmChangeListener);
        ((List) mListResults.get(mListResults.size() - 1)).addAll(jpnWords);
    }

    @Override
    public void onSetNeedSearch(String needSearch) {
        mAdapter.clear();
//        mIsQueryLike = false;
        //
        mNeedSearch = needSearch;
        //
        Log.e(MY_TAG, "onSetNeedSearch: begin " + needSearch);
        if (!mListResults.isEmpty()) {
            if (needSearch.contains(mNeedSearch)) {
                mSearchType = CHAINING_QUERY_1;
                // mNeedSearch: abc
                // needSearch: abcd
                // -> result of 'abcd' is a child of result of 'abc'
                onChainingQuery(needSearch, mListResults.get(mListResults.size() - 1));
            } else if (mNeedSearch.contains(needSearch)) {
                mSearchType = CHAINING_QUERY_2;
                // mListResults.get(needSearch.length() - 1) is result of 'abc'
                // mNeedSearch: abcd
                // needSearch: abc
                // -> result of 'abc' or 'bcd' is a child of result of 'abc'
                onChainingQuery(needSearch, mListResults.get(needSearch.length() - 1));
            }
        } else {
            // search all database
            if (StringHandling.isWordHasKanjis(needSearch)) {
                mSearchType = SEARCH_HAS_KANJI;
                // if has kanji -> search in origin column
                mPresenter.searchEqualHasKanjis(needSearch);
            } else {
                mSearchType = SEARCH_NOT_HAS_KANJI;
                // if not has  kanji -> search in kata column
                mPresenter.searchEqualNotHasKanjis(needSearch);
            }
        }
        mNeedSearch = needSearch;
    }

    @Override
    public void onChainingQuery(String needSearch, RealmResults<JpnWord> parentsResult) {
        mPresenter.chaningQueryEqual(needSearch, parentsResult);
    }

    @Override
    public void onRemoveLastResult() {
        mListResults.get(mListResults.size() - 1).removeAllChangeListeners();
        mListResults.remove(mListResults.size() - 1);
    }

    @Override
    public void onClearRealmResults() {
        mAdapter.setSource(null);
        mListResults.clear();
    }

    @Override
    public void onClick(JpnWord jpnWord) {
        SoftKeybroad.hide((Activity) ((SearchViewModel) mSearchViewModel).getContext());
        mSearchViewModel.onItemClick(mRealm.copyFromRealm(jpnWord));
    }
}
