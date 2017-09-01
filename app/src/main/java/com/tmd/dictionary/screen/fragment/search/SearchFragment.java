package com.tmd.dictionary.screen.fragment.search;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.databinding.FragmentSearchBinding;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.activity.main.MainContract;

import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIEW_MODEL;

/**
 * Search Screen.
 */
public class SearchFragment extends BaseFragment {
    private MainContract.ViewModel mMainViewModel;
    private SearchContract.ViewModel mViewModel;

    public static SearchFragment newInstance(MainContract.ViewModel mainViewModel) {
        SearchFragment vieJavFragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_VIEW_MODEL, mainViewModel);
        vieJavFragment.setArguments(bundle);
        return vieJavFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMainViewModel = getArguments().getParcelable(BUNDLE_VIEW_MODEL);
        }
        mViewModel = new SearchViewModel(mMainViewModel);
        SearchContract.Presenter presenter = new SearchPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentSearchBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        binding.setViewModel((SearchViewModel) mViewModel);
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
