package com.tmd.dictionary.screen.fragment.listboxes;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.source.Repository;
import com.tmd.dictionary.data.source.local.LocalDataSource;
import com.tmd.dictionary.databinding.FragmentListBoxesBinding;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.activity.boxs.BoxesContract;
import com.tmd.dictionary.screen.activity.boxs.BoxesViewModel;

import io.realm.Realm;

import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_BOX_TYPE;
import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIEW_MODEL;

/**
 * JpnBoxes Screen.
 */
public class ListBoxesFragment extends BaseFragment {
    private BoxesContract.ViewModel mBoxesViewModel;
    private ListBoxesContract.ViewModel mViewModel;
    private Realm mRealm;
    private int mBoxType;

    public static ListBoxesFragment newInstance(BoxesContract.ViewModel boxesViewModel,
                                                int boxType) {
        ListBoxesFragment jpnBoxesFragment = new ListBoxesFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_VIEW_MODEL, boxesViewModel);
        bundle.putInt(BUNDLE_BOX_TYPE, boxType);
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
        mBoxType = getArguments().getInt(BUNDLE_BOX_TYPE);
        mViewModel = new ListBoxesViewModel(mBoxType);
        mRealm = ((BoxesViewModel) mBoxesViewModel).getRealm();
        ListBoxesContract.Presenter presenter =
            new ListBoxesPresenter(mViewModel, new Repository(new LocalDataSource(mRealm)));
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentListBoxesBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list_boxes, container, false);
        binding.setViewModel((ListBoxesViewModel) mViewModel);
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
