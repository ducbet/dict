package com.tmd.dictionary.screen.activity.main;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.screen.fragment.grammardetail.GrammarDetailFragment;
import com.tmd.dictionary.screen.fragment.jpndetail.JpnDetailFragment;
import com.tmd.dictionary.screen.fragment.kanjidetail.KanjiDetailFragment;
import com.tmd.dictionary.screen.fragment.search.SearchFragment;
import com.tmd.dictionary.screen.fragment.viedetail.VieDetailFragment;

import java.io.Serializable;

/**
 * Exposes the data to be used in the Main screen.
 */
public class MainViewModel implements MainContract.ViewModel, Serializable {
    private MainContract.Presenter mPresenter;
    private Context mContext;
    private FragmentManager mFragmentManager;

    public MainViewModel(Context context) {
        mContext = context;
        mFragmentManager = ((MainActivity) mContext).getSupportFragmentManager();
        initSearchFragment();
    }

    private void initSearchFragment() {
        mFragmentManager
            .beginTransaction()
            .add(R.id.frame_layout, SearchFragment.newInstance(this))
            .addToBackStack(null)
            .commit();
    }

    public Context getContext() {
        return mContext;
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
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isSearchFragVisibility() {
        return mFragmentManager.getBackStackEntryCount() == 1;
    }

    @Override
    public void onOpenKanjiDetailFragment(Kanji kanji) {
        mFragmentManager
            .beginTransaction()
            .add(R.id.frame_layout, KanjiDetailFragment.newInstance(this, kanji))
            .addToBackStack(null)
            .commit();
    }

    @Override
    public void onOpenJpnWordDetailFragment(JpnWord jpnWord) {
        mFragmentManager
            .beginTransaction()
            .add(R.id.frame_layout, JpnDetailFragment.newInstance(this, jpnWord))
            .addToBackStack(null)
            .commit();
    }

    @Override
    public void onOpenVieWordDetailFragment(VieWord vieWord) {
        mFragmentManager
            .beginTransaction()
            .add(R.id.frame_layout, VieDetailFragment.newInstance(this, vieWord))
            .addToBackStack(null)
            .commit();
    }

    @Override
    public void onOpenGrammarDetailFragment(Grammar grammar) {
        mFragmentManager
            .beginTransaction()
            .add(R.id.frame_layout, GrammarDetailFragment.newInstance(this))
            .addToBackStack(null)
            .commit();
    }
}
