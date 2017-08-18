package com.tmd.dictionary.screen.fragment.JavVie;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.databinding.FragmentJavVieBinding;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.activity.search.SearchContract;

/**
 * JavVie Screen.
 */
public class JavVieFragment extends BaseFragment {
    private static final String BUNDLE_VIEW_MODEL = "BUNDLE_VIEW_MODEL";
    private SearchContract.ViewModel mSearchViewModel;
    private JavVieContract.ViewModel mViewModel;

    public static JavVieFragment newInstance(SearchContract.ViewModel searchViewModel) {
        JavVieFragment javVieFragment = new JavVieFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_VIEW_MODEL, searchViewModel);
        javVieFragment.setArguments(bundle);
        return javVieFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSearchViewModel =
                (SearchContract.ViewModel) getArguments().getSerializable(BUNDLE_VIEW_MODEL);
        }
        mViewModel = new JavVieViewModel(mSearchViewModel);
        JavVieContract.Presenter presenter = new JavViePresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentJavVieBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_jav_vie, container, false);
        binding.setViewModel((JavVieViewModel) mViewModel);
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