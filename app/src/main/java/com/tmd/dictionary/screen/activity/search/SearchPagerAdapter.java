package com.tmd.dictionary.screen.activity.search;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.tmd.dictionary.R;
import com.tmd.dictionary.screen.BaseFragment;

import java.util.List;

/**
 * Created by tmd on 15/08/2017.
 */
public class SearchPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<BaseFragment> mListFragments;

    public SearchPagerAdapter(Context context, List<BaseFragment> listFragments) {
        super(((SearchActivity) context).getSupportFragmentManager());
        mContext = context;
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

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.jav_vie);
            case 1:
                return mContext.getString(R.string.vie_jav);
            case 2:
                return mContext.getString(R.string.kanji);
            case 3:
                return mContext.getString(R.string.grammar);
            default:
                return "";
        }
    }
}
