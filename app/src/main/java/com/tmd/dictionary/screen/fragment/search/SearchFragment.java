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

import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_NEED_SEARCH;
import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIEW_MODEL;

/**
 * Search Screen.
 */
public class SearchFragment extends BaseFragment {
    private MainContract.ViewModel mMainViewModel;
    private SearchContract.ViewModel mViewModel;
    private String mNeedSearch;

    public static SearchFragment newInstance(MainContract.ViewModel mainViewModel) {
        SearchFragment vieJavFragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_VIEW_MODEL, mainViewModel);
        vieJavFragment.setArguments(bundle);
        return vieJavFragment;
    }

    public static SearchFragment newInstance(MainContract.ViewModel mainViewModel,
                                             String needSearch) {
        SearchFragment vieJavFragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_VIEW_MODEL, mainViewModel);
        bundle.putString(BUNDLE_NEED_SEARCH, needSearch);
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
        mViewModel = new SearchViewModel(mMainViewModel, this);
        SearchContract.Presenter presenter = new SearchPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        mNeedSearch = getArguments().getString(BUNDLE_NEED_SEARCH, "");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentSearchBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        binding.setViewModel((SearchViewModel) mViewModel);
        ((SearchViewModel) mViewModel).setTextView(binding.textViewSearch);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
        if (!mNeedSearch.isEmpty()) {
            mViewModel.onSend(mNeedSearch);
        }
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
