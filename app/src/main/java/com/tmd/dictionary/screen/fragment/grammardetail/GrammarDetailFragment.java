package com.tmd.dictionary.screen.fragment.grammardetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.databinding.FragmentGrammarDetailBinding;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.activity.main.MainContract;

import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIEW_MODEL;

/**
 * GrammarDetail Screen.
 */
public class GrammarDetailFragment extends BaseFragment {
    private MainContract.ViewModel mMainViewModel;
    private GrammarDetailContract.ViewModel mViewModel;

    public static GrammarDetailFragment newInstance(MainContract.ViewModel mainViewModel) {
        GrammarDetailFragment vieJavFragment = new GrammarDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_VIEW_MODEL, mainViewModel);
        vieJavFragment.setArguments(bundle);
        return vieJavFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMainViewModel =
                (MainContract.ViewModel) getArguments().getSerializable(BUNDLE_VIEW_MODEL);
        }
        mViewModel = new GrammarDetailViewModel();
        GrammarDetailContract.Presenter presenter = new GrammarDetailPresenter(mViewModel);
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
