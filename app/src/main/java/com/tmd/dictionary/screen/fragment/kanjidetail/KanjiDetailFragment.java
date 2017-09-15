package com.tmd.dictionary.screen.fragment.kanjidetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.source.Repository;
import com.tmd.dictionary.data.source.local.LocalDataSource;
import com.tmd.dictionary.databinding.FragmentKanjiDetailBinding;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.activity.main.MainContract;
import com.tmd.dictionary.screen.activity.main.MainViewModel;

import io.realm.Realm;

import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_KANJI;
import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIEW_MODEL;

/**
 * KanjiDetail Screen.
 */
public class KanjiDetailFragment extends BaseFragment {
    private MainContract.ViewModel mMainViewModel;
    private KanjiDetailContract.ViewModel mViewModel;
    private Kanji mKanji;
    private Realm mRealm;

    public static KanjiDetailFragment newInstance(MainContract.ViewModel mainViewModel,
                                                  Kanji kanji) {
        KanjiDetailFragment kanjiDetailFragment = new KanjiDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_VIEW_MODEL, mainViewModel);
        bundle.putParcelable(BUNDLE_KANJI, kanji);
        kanjiDetailFragment.setArguments(bundle);
        return kanjiDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
            return;
        }
        mMainViewModel = getArguments().getParcelable(BUNDLE_VIEW_MODEL);
        mKanji = getArguments().getParcelable(BUNDLE_KANJI);
        mViewModel = new KanjiDetailViewModel(mMainViewModel, mKanji);
        mRealm = ((MainViewModel) mMainViewModel).getRealm();
        KanjiDetailContract.Presenter presenter =
            new KanjiDetailPresenter(mViewModel, new Repository(new LocalDataSource(mRealm)));
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
