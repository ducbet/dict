package com.tmd.dictionary.screen.activity.search;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by tmd on 15/08/2017.
 */
public class SearchPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mListFragments;

    public SearchPagerAdapter(FragmentManager fm, List<Fragment> listFragments) {
        super(fm);
        mListFragments = listFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mListFragments == null ? null : mListFragments.get(position);
    }

    @Override
    public int getCount() {
        return mListFragments != null ? mListFragments.size() : 0;
    }
}
