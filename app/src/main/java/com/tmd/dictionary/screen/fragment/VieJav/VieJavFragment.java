package com.tmd.dictionary.screen.fragment.VieJav;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.source.Repository;
import com.tmd.dictionary.data.source.local.LocalDataSource;
import com.tmd.dictionary.databinding.FragmentVieJavBinding;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.activity.search.SearchContract;
import com.tmd.dictionary.screen.activity.search.SearchViewModel;

import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIEW_MODEL;

/**
 * VieJav Screen.
 */
public class VieJavFragment extends BaseFragment {
    private static final String TAG = VieJavFragment.class.getName();
    private SearchContract.ViewModel mSearchViewModel;
    private VieJavContract.ViewModel mViewModel;

    public static VieJavFragment newInstance(SearchContract.ViewModel searchViewModel) {
        VieJavFragment vieJavFragment = new VieJavFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_VIEW_MODEL, searchViewModel);
        vieJavFragment.setArguments(bundle);
        return vieJavFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSearchViewModel =
                (SearchContract.ViewModel) getArguments().getSerializable(BUNDLE_VIEW_MODEL);
        }
        mViewModel = new VieJavViewModel(mSearchViewModel);
        VieJavContract.Presenter presenter = new VieJavPresenter(mViewModel,
            new Repository(new LocalDataSource(((SearchViewModel) mSearchViewModel).getContext())));
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentVieJavBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_vie_jav, container, false);
        binding.setViewModel((VieJavViewModel) mViewModel);
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
}
