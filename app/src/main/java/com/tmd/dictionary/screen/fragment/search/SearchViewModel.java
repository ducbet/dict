package com.tmd.dictionary.screen.fragment.search;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.tmd.dictionary.BR;
import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.screen.BaseFragmentLevel2;
import com.tmd.dictionary.screen.activity.main.MainContract;
import com.tmd.dictionary.screen.activity.main.MainViewModel;
import com.tmd.dictionary.screen.fragment.search.level2.grammar.GrammarFragment;
import com.tmd.dictionary.screen.fragment.search.level2.javvie.JavVieFragment;
import com.tmd.dictionary.screen.fragment.search.level2.kanji.KanjiFragment;
import com.tmd.dictionary.screen.fragment.search.level2.viejav.VieJavFragment;
import com.tmd.dictionary.staticfinal.StringHandling;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * Exposes the data to be used in the Search screen.
 */
public class SearchViewModel extends BaseObservable
    implements SearchContract.ViewModel, Parcelable {
    private static final String TAG = SearchViewModel.class.getName();
    private MainContract.ViewModel mMainViewModel;
    private SearchFragment mSearchFragment;
    private Context mContext;
    private SearchContract.Presenter mPresenter;
    private SearchPagerAdapter mPagerAdapter;
    private List<BaseFragmentLevel2> mListFragments;
    private String mNeedSearch = "";
    private Disposable mDisposable;
    private Realm mRealm;

    public SearchViewModel(MainContract.ViewModel mainViewModel, SearchFragment searchFragment) {
        mMainViewModel = mainViewModel;
        mSearchFragment = searchFragment;
        mRealm = ((MainViewModel) mMainViewModel).getRealm();
        mContext = ((MainViewModel) mMainViewModel).getContext();
        initViewPager();
    }

    protected SearchViewModel(Parcel in) {
        mNeedSearch = in.readString();
    }

    public static final Creator<SearchViewModel> CREATOR = new Creator<SearchViewModel>() {
        @Override
        public SearchViewModel createFromParcel(Parcel in) {
            return new SearchViewModel(in);
        }

        @Override
        public SearchViewModel[] newArray(int size) {
            return new SearchViewModel[size];
        }
    };

    private void initViewPager() {
        mListFragments = new ArrayList<>();
        mListFragments.add(JavVieFragment.newInstance(this));
        mListFragments.add(KanjiFragment.newInstance(this));
        mListFragments.add(VieJavFragment.newInstance(this));
        mListFragments.add(GrammarFragment.newInstance(this));
        mPagerAdapter = new SearchPagerAdapter(mSearchFragment, mListFragments);
    }

    public List<BaseFragmentLevel2> getListFragments() {
        return mListFragments;
    }

    public Realm getRealm() {
        return mRealm;
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
                    if (s.isEmpty()) {
                        for (BaseFragmentLevel2 fragment : mListFragments) {
                            fragment.onClearRealmResults();
                        }
                        return false;
                    }
                    if (!mNeedSearch.equals(s) && StringHandling.isUnion(s)) {
                        return true;
                    }
                    return false;
                }
            })
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<String>() {
                @Override
                public void accept(@NonNull String s) throws Exception {
                    onSend(s);
                }
            });
    }

    @Override
    public void onSend(String needSearch) {
        setNeedSearch(needSearch);
        for (BaseFragmentLevel2 fragment : mListFragments) {
            fragment.onSetNeedSearch(needSearch);
        }
    }

    @Override
    public void onItemClick(JpnWord jpnWord) {
        mMainViewModel.onOpenJpnWordDetailFragment(jpnWord);
    }

    @Override
    public void onItemClick(VieWord vieWord) {
        mMainViewModel.onOpenVieWordDetailFragment(vieWord);
    }

    @Override
    public void onItemClick(Grammar grammar) {
        mMainViewModel.onOpenGrammarDetailFragment(grammar);
    }

    @Override
    public void onItemClick(Kanji kanji) {
        mMainViewModel.onOpenKanjiDetailFragment(kanji);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mNeedSearch);
    }
}
