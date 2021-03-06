package com.tmd.dictionary.screen.fragment.viedetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.data.source.Repository;
import com.tmd.dictionary.data.source.local.LocalDataSource;
import com.tmd.dictionary.databinding.FragmentVieDetailBinding;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.activity.main.MainContract;
import com.tmd.dictionary.screen.activity.main.MainViewModel;

import io.realm.Realm;

import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIEW_MODEL;
import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIE_WORD;

/**
 * VieDetail Screen.
 */
public class VieDetailFragment extends BaseFragment {
    private MainContract.ViewModel mMainViewModel;
    private VieDetailContract.ViewModel mViewModel;
    private VieWord mVieWord;
    private Realm mRealm;

    public static VieDetailFragment newInstance(MainContract.ViewModel mainViewModel,
                                                VieWord vieWord) {
        VieDetailFragment vieJavFragment = new VieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_VIEW_MODEL, mainViewModel);
        bundle.putParcelable(BUNDLE_VIE_WORD, vieWord);
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
        mVieWord = getArguments().getParcelable(BUNDLE_VIE_WORD);
        mViewModel = new VieDetailViewModel(mMainViewModel, mVieWord);
        mRealm = ((MainViewModel) mMainViewModel).getRealm();
        VieDetailContract.Presenter presenter =
            new VieDetailPresenter(mViewModel, new Repository(new LocalDataSource(mRealm)));
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentVieDetailBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_vie_detail, container, false);
        binding.setViewModel((VieDetailViewModel) mViewModel);
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
