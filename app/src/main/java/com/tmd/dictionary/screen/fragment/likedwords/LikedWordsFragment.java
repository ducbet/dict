package com.tmd.dictionary.screen.fragment.likedwords;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.source.Repository;
import com.tmd.dictionary.data.source.local.LocalDataSource;
import com.tmd.dictionary.databinding.FragmentLikedWordsBinding;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.activity.main.MainContract;
import com.tmd.dictionary.screen.activity.main.MainViewModel;

import io.realm.Realm;

import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIEW_MODEL;

/**
 * Liked Screen.
 */
public class LikedWordsFragment extends BaseFragment {
    private MainContract.ViewModel mMainViewModel;
    private LikedWordsContract.ViewModel mViewModel;
    private Realm mRealm;

    public static LikedWordsFragment newInstance(MainContract.ViewModel mainViewModel) {
        LikedWordsFragment likedWordsFragment = new LikedWordsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_VIEW_MODEL, mainViewModel);
        likedWordsFragment.setArguments(bundle);
        return likedWordsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
            return;
        }
        mMainViewModel = getArguments().getParcelable(BUNDLE_VIEW_MODEL);
        mViewModel = new LikedWordsViewModel();
        mRealm = ((MainViewModel) mMainViewModel).getRealm();
        LikedWordsContract.Presenter presenter =
            new LikedWordsPresenter(mViewModel, new Repository(new LocalDataSource(mRealm)));
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentLikedWordsBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_liked_words, container, false);
        binding.setViewModel((LikedWordsViewModel) mViewModel);
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
