package com.tmd.dictionary.screen.fragment.history;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.source.Repository;
import com.tmd.dictionary.data.source.local.LocalDataSource;
import com.tmd.dictionary.databinding.FragmentHistoryBinding;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.activity.main.MainContract;
import com.tmd.dictionary.screen.activity.main.MainViewModel;

import io.realm.Realm;

import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIEW_MODEL;

/**
 * History Screen.
 */
public class HistoryFragment extends BaseFragment {
    private MainContract.ViewModel mMainViewModel;
    private HistoryContract.ViewModel mViewModel;
    private Realm mRealm;

    public static HistoryFragment newInstance(MainContract.ViewModel mainViewModel) {
        HistoryFragment historyFragment = new HistoryFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_VIEW_MODEL, mainViewModel);
        historyFragment.setArguments(bundle);
        return historyFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
            return;
        }
        mMainViewModel = getArguments().getParcelable(BUNDLE_VIEW_MODEL);
        mViewModel = new HistoryViewModel();
        mRealm = ((MainViewModel) mMainViewModel).getRealm();
        HistoryContract.Presenter presenter =
            new HistoryPresenter(mViewModel, new Repository(new LocalDataSource(mRealm)));
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentHistoryBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
        binding.setViewModel((HistoryViewModel) mViewModel);
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
