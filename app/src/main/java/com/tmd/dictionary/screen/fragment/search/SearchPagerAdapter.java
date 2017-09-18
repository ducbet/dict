package com.tmd.dictionary.screen.fragment.search;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.tmd.dictionary.R;
import com.tmd.dictionary.screen.BaseFragmentLevel2;
import com.tmd.dictionary.screen.fragment.search.level2.grammar.GrammarFragment;
import com.tmd.dictionary.screen.fragment.search.level2.javvie.JavVieFragment;
import com.tmd.dictionary.screen.fragment.search.level2.kanji.KanjiFragment;
import com.tmd.dictionary.screen.fragment.search.level2.viejav.VieJavFragment;

import java.util.List;

/**
 * Created by tmd on 15/08/2017.
 */
public class SearchPagerAdapter extends FragmentPagerAdapter {
    private SearchFragment mSearchFragment;
    private List<BaseFragmentLevel2> mListFragments;

    public SearchPagerAdapter(SearchFragment searchFragment,
                              List<BaseFragmentLevel2> listFragments) {
        super(searchFragment.getChildFragmentManager());
        mSearchFragment = searchFragment;
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
            return mSearchFragment.getString(R.string.jav_vie);
        }
        if (mListFragments.get(position) instanceof VieJavFragment) {
            return mSearchFragment.getString(R.string.vie_jav);
        }
        if (mListFragments.get(position) instanceof KanjiFragment) {
            return mSearchFragment.getString(R.string.kanji);
        }
        if (mListFragments.get(position) instanceof GrammarFragment) {
            return mSearchFragment.getString(R.string.grammar);
        }
        return "";
    }
}
