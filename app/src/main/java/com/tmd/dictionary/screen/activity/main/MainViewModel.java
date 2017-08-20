package com.tmd.dictionary.screen.activity.main;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.screen.fragment.jpnworddetail.JpnDetailFragment;
import com.tmd.dictionary.screen.fragment.search.SearchFragment;

/**
 * Exposes the data to be used in the Main screen.
 */
public class MainViewModel implements MainContract.ViewModel {
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
    }

    @Override
    public void onOpenJpnWordDetailFragment(Word word) {
        mFragmentManager
            .beginTransaction()
            .add(R.id.frame_layout, JpnDetailFragment.newInstance(this))
            .addToBackStack(null)
            .commit();
    }

    @Override
    public void onOpenVieWordDetailFragment(Word word) {
    }

    @Override
    public void onOpenGrammarDetailFragment(Word word) {
    }
}
