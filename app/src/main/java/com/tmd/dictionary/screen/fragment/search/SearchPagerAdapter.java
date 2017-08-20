package com.tmd.dictionary.screen.fragment.search;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.tmd.dictionary.R;
import com.tmd.dictionary.screen.BaseFragmentLevel2;
import com.tmd.dictionary.screen.activity.main.MainActivity;
import com.tmd.dictionary.screen.fragment.search.level2.grammar.GrammarFragment;
import com.tmd.dictionary.screen.fragment.search.level2.javvie.JavVieFragment;
import com.tmd.dictionary.screen.fragment.search.level2.kanji.KanjiFragment;
import com.tmd.dictionary.screen.fragment.search.level2.viejav.VieJavFragment;

import java.util.List;

/**
 * Created by tmd on 15/08/2017.
 */
public class SearchPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<BaseFragmentLevel2> mListFragments;

    public SearchPagerAdapter(Context context, List<BaseFragmentLevel2> listFragments) {
        super(((MainActivity) context).getSupportFragmentManager());
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
