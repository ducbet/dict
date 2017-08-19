package com.tmd.dictionary.screen.activity.search;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.tmd.dictionary.R;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.fragment.Grammar.GrammarFragment;
import com.tmd.dictionary.screen.fragment.JavVie.JavVieFragment;
import com.tmd.dictionary.screen.fragment.Kanji.KanjiFragment;
import com.tmd.dictionary.screen.fragment.VieJav.VieJavFragment;

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
        if (mListFragments.get(position) instanceof JavVieFragment) {
            return mContext.getString(R.string.jav_vie);
        }
        if (mListFragments.get(position) instanceof VieJavFragment) {
            return mContext.getString(R.string.vie_jav);
        }
        if (mListFragments.get(position) instanceof KanjiFragment) {
            return mContext.getString(R.string.kanji);
        }
        if (mListFragments.get(position) instanceof GrammarFragment) {
            return mContext.getString(R.string.grammar);
        }
        return "";
    }
}
