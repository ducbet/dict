package com.tmd.dictionary.screen.fragment.learningjpn;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.JpnBox;
import com.tmd.dictionary.data.source.Repository;
import com.tmd.dictionary.data.source.local.LocalDataSource;
import com.tmd.dictionary.databinding.FragmentLearningJpnBinding;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.fragment.listboxes.ListBoxesContract;
import com.tmd.dictionary.screen.fragment.listboxes.ListBoxesViewModel;

import io.realm.Realm;

import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_JPN_BOX;
import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIEW_MODEL;

/**
 * LearningJpn Screen.
 */
public class LearningJpnFragment extends BaseFragment {
    private LearningJpnContract.ViewModel mViewModel;
    private ListBoxesContract.ViewModel mListBoxesViewModel;
    private JpnBox mJpnBox;
    private Realm mRealm;

    public static LearningJpnFragment newInstance(ListBoxesContract.ViewModel listBoxesViewModel,
                                                  JpnBox box) {
        LearningJpnFragment learningJpnFragment = new LearningJpnFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_VIEW_MODEL, listBoxesViewModel);
        bundle.putParcelable(BUNDLE_JPN_BOX, box);
        learningJpnFragment.setArguments(bundle);
        return learningJpnFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
            return;
        }
        mListBoxesViewModel = getArguments().getParcelable(BUNDLE_VIEW_MODEL);
        mJpnBox = getArguments().getParcelable(BUNDLE_JPN_BOX);
        mRealm = ((ListBoxesViewModel) mListBoxesViewModel).getRealm();
        mViewModel = new LearningJpnViewModel(mListBoxesViewModel, mJpnBox, mRealm);
        LearningJpnContract.Presenter presenter =
            new LearningJpnPresenter(mViewModel, new Repository(new LocalDataSource(mRealm)));
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentLearningJpnBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_learning_jpn, container, false);
        binding.setViewModel((LearningJpnViewModel) mViewModel);
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
