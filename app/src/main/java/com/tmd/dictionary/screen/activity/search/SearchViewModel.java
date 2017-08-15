package com.tmd.dictionary.screen.activity.search;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.tmd.dictionary.screen.fragment.JavVie.JavVieFragment;
import com.tmd.dictionary.screen.fragment.VieJav.VieJavFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the Search screen.
 */
public class SearchViewModel implements SearchContract.ViewModel {
    private Context mContext;
    private SearchContract.Presenter mPresenter;
    private SearchPagerAdapter mPagerAdapter;
    private List<Fragment> mListFragments;

    public SearchViewModel(Context context) {
        mContext = context;
        initViewPager();
    }

    private void initViewPager() {
        mListFragments = new ArrayList<>();
        mListFragments.add(JavVieFragment.newInstance(this));
        mListFragments.add(VieJavFragment.newInstance(this));
        mPagerAdapter = new SearchPagerAdapter(mContext, mListFragments);
    }

    public List<Fragment> getListFragments() {
        return mListFragments;
    }

    public SearchPagerAdapter getPagerAdapter() {
        return mPagerAdapter;
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
}
