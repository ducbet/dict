package com.tmd.dictionary.screen.fragment.Grammar;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.databinding.FragmentGrammarBinding;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.activity.search.SearchContract;

/**
 * Grammar Screen.
 */
public class GrammarFragment extends BaseFragment {
    private static final String BUNDLE_VIEW_MODEL = "BUNDLE_VIEW_MODEL";
    private SearchContract.ViewModel mSearchViewModel;
    private GrammarContract.ViewModel mViewModel;

    public static GrammarFragment newInstance(SearchContract.ViewModel searchViewModel) {
        GrammarFragment grammarFragment = new GrammarFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_VIEW_MODEL, searchViewModel);
        grammarFragment.setArguments(bundle);
        return grammarFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSearchViewModel =
                (SearchContract.ViewModel) getArguments().getSerializable(BUNDLE_VIEW_MODEL);
        }
        mViewModel = new GrammarViewModel(mSearchViewModel);
        GrammarContract.Presenter presenter = new GrammarPresenter(mViewModel);
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
}
