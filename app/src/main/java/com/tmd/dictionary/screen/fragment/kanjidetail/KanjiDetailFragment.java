package com.tmd.dictionary.screen.fragment.kanjidetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.databinding.FragmentKanjiDetailBinding;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.activity.main.MainContract;

import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIEW_MODEL;

/**
 * KanjiDetail Screen.
 */
public class KanjiDetailFragment extends BaseFragment {
    private MainContract.ViewModel mMainViewModel;
    private KanjiDetailContract.ViewModel mViewModel;

    public static KanjiDetailFragment newInstance(MainContract.ViewModel mainViewModel) {
        KanjiDetailFragment vieJavFragment = new KanjiDetailFragment();
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
        mViewModel = new KanjiDetailViewModel();
        KanjiDetailContract.Presenter presenter = new KanjiDetailPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentKanjiDetailBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_kanji_detail, container, false);
        binding.setViewModel((KanjiDetailViewModel) mViewModel);
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
