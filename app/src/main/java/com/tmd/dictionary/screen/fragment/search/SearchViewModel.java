package com.tmd.dictionary.screen.fragment.search;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.tmd.dictionary.BR;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.screen.BaseFragmentLevel2;
import com.tmd.dictionary.screen.BaseViewModel;
import com.tmd.dictionary.screen.activity.main.MainContract;
import com.tmd.dictionary.screen.activity.main.MainViewModel;
import com.tmd.dictionary.screen.fragment.search.level2.grammar.GrammarFragment;
import com.tmd.dictionary.screen.fragment.search.level2.grammar.GrammarViewModel;
import com.tmd.dictionary.screen.fragment.search.level2.javvie.JavVieFragment;
import com.tmd.dictionary.screen.fragment.search.level2.javvie.JavVieViewModel;
import com.tmd.dictionary.screen.fragment.search.level2.kanji.KanjiFragment;
import com.tmd.dictionary.screen.fragment.search.level2.viejav.VieJavFragment;
import com.tmd.dictionary.screen.fragment.search.level2.viejav.VieJavViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.tmd.dictionary.staticfinal.ConstantValue.MY_TAG;

/**
 * Exposes the data to be used in the Search screen.
 */
public class SearchViewModel extends BaseObservable implements SearchContract.ViewModel {
    private MainContract.ViewModel mMainViewModel;
    private Context mContext;
    private SearchContract.Presenter mPresenter;
    private SearchPagerAdapter mPagerAdapter;
    private List<BaseFragmentLevel2> mListFragments;
    private String mNeedSearch;

    public SearchViewModel(MainContract.ViewModel mainViewModel) {
        mMainViewModel = mainViewModel;
        mContext = ((MainViewModel) mMainViewModel).getContext();
        initViewPager();
    }

    private void initViewPager() {
        mListFragments = new ArrayList<>();
        mListFragments.add(JavVieFragment.newInstance(this));
        mListFragments.add(KanjiFragment.newInstance(this));
        mListFragments.add(VieJavFragment.newInstance(this));
        mListFragments.add(GrammarFragment.newInstance(this));
        mPagerAdapter = new SearchPagerAdapter(mContext, mListFragments);
    }

    public List<BaseFragmentLevel2> getListFragments() {
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
        for (BaseFragmentLevel2 fragment : mListFragments) {
            fragment.onSetNeedSearch(needSearch);
        }
    }

    @Override
    public void onItemClick(BaseViewModel viewModel, Word word) {
        if (viewModel instanceof JavVieViewModel){
            mMainViewModel.onOpenJpnWordDetailFragment(word);
            Log.e(MY_TAG, "onItemClick: ");
            return;
        }
        if (viewModel instanceof VieJavViewModel){
            mMainViewModel.onOpenVieWordDetailFragment(word);
            return;
        }
        if (viewModel instanceof GrammarViewModel){
            mMainViewModel.onOpenGrammarDetailFragment(word);
            return;
        }
    }

    @Override
    public void onItemClick(BaseViewModel viewModel, Kanji kanji) {
        mMainViewModel.onOpenKanjiDetailFragment(kanji);
    }
}
