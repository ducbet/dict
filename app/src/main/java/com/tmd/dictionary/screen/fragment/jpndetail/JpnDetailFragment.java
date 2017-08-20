package com.tmd.dictionary.screen.fragment.jpndetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.databinding.FragmentJpnDetailBinding;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.activity.main.MainContract;

import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIEW_MODEL;
import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_WORD;

/**
 * JpnWordDetail Screen.
 */
public class JpnDetailFragment extends BaseFragment {
    private MainContract.ViewModel mMainViewModel;
    private JpnDetailContract.ViewModel mViewModel;
    private Word mWord;

    public static JpnDetailFragment newInstance(MainContract.ViewModel mainViewModel, Word word) {
        JpnDetailFragment vieJavFragment = new JpnDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_VIEW_MODEL, mainViewModel);
        bundle.putSerializable(BUNDLE_WORD, word);
        vieJavFragment.setArguments(bundle);
        return vieJavFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMainViewModel =
                (MainContract.ViewModel) getArguments().getSerializable(BUNDLE_VIEW_MODEL);
            mWord = (Word) getArguments().getSerializable(BUNDLE_WORD);
        }
        mViewModel = new JpnDetailViewModel(mWord);
        JpnDetailContract.Presenter presenter = new JpnDetailPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentJpnDetailBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_jpn_detail, container, false);
        binding.setViewModel((JpnDetailViewModel) mViewModel);
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
