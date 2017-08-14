package com.tmd.dictionary.screen.search;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.tmd.dictionary.R;
import com.tmd.dictionary.databinding.ActivitySearchBinding;
import com.tmd.dictionary.screen.BaseActivity;

/**
 * Search Screen.
 */
public class SearchActivity extends BaseActivity {
    private SearchContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new SearchViewModel();
        SearchContract.Presenter presenter =
            new SearchPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        ActivitySearchBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.setViewModel((SearchViewModel) mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
