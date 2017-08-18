package com.tmd.dictionary.screen.activity.search;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tmd.dictionary.BR;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.fragment.Grammar.GrammarFragment;
import com.tmd.dictionary.screen.fragment.JavVie.JavVieFragment;
import com.tmd.dictionary.screen.fragment.Kanji.KanjiFragment;
import com.tmd.dictionary.screen.fragment.VieJav.VieJavFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the Search screen.
 */
public class SearchViewModel extends BaseObservable implements SearchContract.ViewModel {
    private Context mContext;
    private SearchContract.Presenter mPresenter;
    private SearchPagerAdapter mPagerAdapter;
    private List<BaseFragment> mListFragments;
    private String mNeedSearch;

    public SearchViewModel(Context context) {
        mContext = context;
        initViewPager();
    }

    private void initViewPager() {
        mListFragments = new ArrayList<>();
        mListFragments.add(JavVieFragment.newInstance(this));
        mListFragments.add(VieJavFragment.newInstance(this));
        mListFragments.add(KanjiFragment.newInstance(this));
        mListFragments.add(GrammarFragment.newInstance(this));
        mPagerAdapter = new SearchPagerAdapter(mContext, mListFragments);
    }

    public List<BaseFragment> getListFragments() {
        return mListFragments;
    }

    public Context getContext() {
        return mContext;
    }

    public SearchPagerAdapter getPagerAdapter() {
        return mPagerAdapter;
    }

    @Bindable
    public String getNeedSearch() {
        return mNeedSearch;
    }

    public void setNeedSearch(String needSearch) {
        mNeedSearch = needSearch;
        notifyPropertyChanged(BR.needSearch);
        if (!needSearch.isEmpty()) {
            onSendToAllFragment(needSearch);
        }
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
    public void setPresenter(SearchContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onSendToAllFragment(String needSearch) {
        for (BaseFragment fragment : mListFragments) {
            fragment.onSetNeedSearch(needSearch);
        }
    }
}
