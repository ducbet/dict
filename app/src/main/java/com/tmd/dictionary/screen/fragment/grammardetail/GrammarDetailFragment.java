package com.tmd.dictionary.screen.fragment.grammardetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.source.Repository;
import com.tmd.dictionary.data.source.local.LocalDataSource;
import com.tmd.dictionary.databinding.FragmentGrammarDetailBinding;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.activity.main.MainContract;
import com.tmd.dictionary.screen.activity.main.MainViewModel;

import io.realm.Realm;

import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_GRAMMAR;
import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIEW_MODEL;

/**
 * GrammarDetail Screen.
 */
public class GrammarDetailFragment extends BaseFragment {
    private MainContract.ViewModel mMainViewModel;
    private GrammarDetailContract.ViewModel mViewModel;
    private Grammar mGrammar;
    private Realm mRealm;

    public static GrammarDetailFragment newInstance(MainContract.ViewModel mainViewModel,
                                                    Grammar grammar) {
        GrammarDetailFragment vieJavFragment = new GrammarDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_VIEW_MODEL, mainViewModel);
        bundle.putParcelable(BUNDLE_GRAMMAR, grammar);
        vieJavFragment.setArguments(bundle);
        return vieJavFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
            return;
        }
        mMainViewModel = getArguments().getParcelable(BUNDLE_VIEW_MODEL);
        mGrammar = getArguments().getParcelable(BUNDLE_GRAMMAR);
        mViewModel = new GrammarDetailViewModel(mMainViewModel, mGrammar);
        mRealm = ((MainViewModel) mMainViewModel).getRealm();
        GrammarDetailContract.Presenter presenter =
            new GrammarDetailPresenter(mViewModel, new Repository(new LocalDataSource(mRealm)));
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentGrammarDetailBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_grammar_detail, container, false);
        binding.setViewModel((GrammarDetailViewModel) mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
}
