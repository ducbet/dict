package com.tmd.dictionary.screen.fragment.search.level2.kanji;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.source.Repository;
import com.tmd.dictionary.data.source.local.LocalDataSource;
import com.tmd.dictionary.databinding.FragmentKanjiBinding;
import com.tmd.dictionary.screen.BaseFragment;
import com.tmd.dictionary.screen.fragment.search.SearchContract;
import com.tmd.dictionary.screen.fragment.search.SearchViewModel;

import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIEW_MODEL;

/**
 * Kanji Screen.
 */
public class KanjiFragment extends BaseFragment {
    private static final String TAG = KanjiFragment.class.getName();
    private SearchContract.ViewModel mSearchViewModel;
    private KanjiContract.ViewModel mViewModel;

    public static KanjiFragment newInstance(SearchContract.ViewModel searchViewModel) {
        KanjiFragment kanjiFragment = new KanjiFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_VIEW_MODEL, searchViewModel);
        kanjiFragment.setArguments(bundle);
        return kanjiFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSearchViewModel =
                (SearchContract.ViewModel) getArguments().getSerializable(BUNDLE_VIEW_MODEL);
        }
        mViewModel = new KanjiViewModel(mSearchViewModel);
        KanjiContract.Presenter presenter = new KanjiPresenter(mViewModel,
            new Repository(new LocalDataSource(((SearchViewModel) mSearchViewModel).getContext())));
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentKanjiBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_kanji, container, false);
        binding.setViewModel((KanjiViewModel) mViewModel);
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

    @Override
    public void onSetNeedSearch(String needSearch) {
        if (mViewModel == null) {
            return;
        }
        mViewModel.onSetNeedSearch(needSearch);
    }
}
