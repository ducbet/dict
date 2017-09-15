package com.tmd.dictionary.screen.fragment.jpndetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.source.Repository;
import com.tmd.dictionary.data.source.local.LocalDataSource;
import com.tmd.dictionary.databinding.FragmentJpnDetailBinding;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.activity.main.MainContract;
import com.tmd.dictionary.screen.activity.main.MainViewModel;

import io.realm.Realm;

import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_JPN_WORD;
import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIEW_MODEL;

/**
 * JpnWordDetail Screen.
 */
public class JpnDetailFragment extends BaseFragment {
    private MainContract.ViewModel mMainViewModel;
    private JpnDetailContract.ViewModel mViewModel;
    private JpnWord mJpnWord;
    private Realm mRealm;

    public static JpnDetailFragment newInstance(MainContract.ViewModel mainViewModel,
                                                JpnWord jpnWord) {
        JpnDetailFragment vieJavFragment = new JpnDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_VIEW_MODEL, mainViewModel);
        bundle.putParcelable(BUNDLE_JPN_WORD, jpnWord);
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
        mJpnWord = getArguments().getParcelable(BUNDLE_JPN_WORD);
        mViewModel = new JpnDetailViewModel(mMainViewModel, mJpnWord);
        mRealm = ((MainViewModel) mMainViewModel).getRealm();
        JpnDetailContract.Presenter presenter =
            new JpnDetailPresenter(mViewModel, new Repository(new LocalDataSource(mRealm)));
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
