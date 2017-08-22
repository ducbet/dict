package com.tmd.dictionary.screen.fragment.search;

import android.content.Context;
import android.databinding.BaseObservable;

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
import com.tmd.dictionary.staticfinal.StringHandling;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import static com.tmd.dictionary.staticfinal.ConstantValue.DELAY_SEARCH;

/**
 * Exposes the data to be used in the Search screen.
 */
public class SearchViewModel extends BaseObservable implements SearchContract.ViewModel {
    private static final String TAG = SearchViewModel.class.getName();
    private MainContract.ViewModel mMainViewModel;
    private Context mContext;
    private SearchContract.Presenter mPresenter;
    private SearchPagerAdapter mPagerAdapter;
    private List<BaseFragmentLevel2> mListFragments;
    private String mNeedSearch = "";
    private Disposable mDisposable;

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

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onSendToAllFragment(Observable<String> textChangeObservable) {
        mDisposable = textChangeObservable
            .filter(new Predicate<String>() {
                @Override
                public boolean test(@NonNull String s) throws Exception {
                    if (!s.isEmpty() && !mNeedSearch.equals(s) && StringHandling.isUnion(s)) {
                        return true;
                    }
                    return false;
                }
            })
            .debounce(DELAY_SEARCH, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<String>() {
                @Override
                public void accept(@NonNull String s) throws Exception {
                    for (BaseFragmentLevel2 fragment : mListFragments) {
                        fragment.onSetNeedSearch(s);
                    }
                }
            });
    }

    @Override
    public void onItemClick(BaseViewModel viewModel, Word word) {
        if (viewModel instanceof JavVieViewModel) {
            mMainViewModel.onOpenJpnWordDetailFragment(word);
            return;
        }
        if (viewModel instanceof VieJavViewModel) {
            mMainViewModel.onOpenVieWordDetailFragment(word);
            return;
        }
        if (viewModel instanceof GrammarViewModel) {
            mMainViewModel.onOpenGrammarDetailFragment(word);
            return;
        }
    }

    @Override
    public void onItemClick(Kanji kanji) {
        mMainViewModel.onOpenKanjiDetailFragment(kanji);
    }
}
