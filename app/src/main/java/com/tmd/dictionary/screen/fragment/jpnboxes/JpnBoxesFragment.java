package com.tmd.dictionary.screen.fragment.jpnboxes;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.source.Repository;
import com.tmd.dictionary.data.source.local.LocalDataSource;
import com.tmd.dictionary.databinding.FragmentJpnBoxesBinding;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.activity.boxs.BoxesContract;
import com.tmd.dictionary.screen.activity.boxs.BoxesViewModel;

import io.realm.Realm;

import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIEW_MODEL;

/**
 * JpnBoxes Screen.
 */
public class JpnBoxesFragment extends BaseFragment {
    private BoxesContract.ViewModel mBoxesViewModel;
    private JpnBoxesContract.ViewModel mViewModel;
    private Realm mRealm;

    public static JpnBoxesFragment newInstance(BoxesContract.ViewModel boxesViewModel) {
        JpnBoxesFragment jpnBoxesFragment = new JpnBoxesFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_VIEW_MODEL, boxesViewModel);
        jpnBoxesFragment.setArguments(bundle);
        return jpnBoxesFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
            return;
        }
        mBoxesViewModel = getArguments().getParcelable(BUNDLE_VIEW_MODEL);
        mViewModel = new JpnBoxesViewModel();
        mRealm = ((BoxesViewModel) mBoxesViewModel).getRealm();
        JpnBoxesContract.Presenter presenter =
            new JpnBoxesPresenter(mViewModel, new Repository(new LocalDataSource(mRealm)));
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentJpnBoxesBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_jpn_boxes, container, false);
        binding.setViewModel((JpnBoxesViewModel) mViewModel);
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
