package com.tmd.dictionary.screen.fragment.search.level2.grammar;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.source.Repository;
import com.tmd.dictionary.data.source.local.LocalDataSource;
import com.tmd.dictionary.databinding.FragmentGrammarBinding;
import com.tmd.dictionary.screen.BaseFragmentLevel2;
import com.tmd.dictionary.screen.fragment.search.SearchContract;
import com.tmd.dictionary.screen.fragment.search.SearchViewModel;

import io.realm.Realm;

import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIEW_MODEL;

/**
 * Grammar Screen.
 */
public class GrammarFragment extends BaseFragmentLevel2 {
    private static final String TAG = GrammarFragment.class.getName();
    private SearchContract.ViewModel mSearchViewModel;
    private GrammarContract.ViewModel mViewModel;
    private Realm mRealm;

    public static GrammarFragment newInstance(SearchContract.ViewModel searchViewModel) {
        GrammarFragment grammarFragment = new GrammarFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_VIEW_MODEL, searchViewModel);
        grammarFragment.setArguments(bundle);
        return grammarFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
            return;
        }
        mSearchViewModel = getArguments().getParcelable(BUNDLE_VIEW_MODEL);
        mRealm = ((SearchViewModel) mSearchViewModel).getRealm();
        mViewModel = new GrammarViewModel(mRealm, mSearchViewModel);
        GrammarContract.Presenter presenter =
            new GrammarPresenter(mViewModel, new Repository(new LocalDataSource(mRealm)));
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentGrammarBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_grammar, container, false);
        binding.setViewModel((GrammarViewModel) mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }

    @Override
    public void onSetNeedSearch(String needSearch) {
        if (mViewModel == null) {
            return;
        }
        mViewModel.onSetNeedSearch(needSearch);
    }

    @Override
    public void onClearRealmResults() {
        if (mViewModel == null) {
            return;
        }
        mViewModel.onClearRealmResults();
    }
}
