package com.tmd.dictionary.screen.activity.search;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.source.Repository;
import com.tmd.dictionary.data.source.local.DatabaseHelper;
import com.tmd.dictionary.data.source.local.LocalDataSource;
import com.tmd.dictionary.databinding.ActivitySearchBinding;
import com.tmd.dictionary.screen.BaseActivity;

import java.io.IOException;

/**
 * Search Screen.
 */
public class SearchActivity extends BaseActivity {
    private SearchContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new SearchViewModel(this);
        SearchContract.Presenter presenter =
            new SearchPresenter(mViewModel, new Repository(new LocalDataSource(this)));
        mViewModel.setPresenter(presenter);
        ActivitySearchBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.setViewModel((SearchViewModel) mViewModel);
        try {
            DatabaseHelper.copyDataBase(this);
        } catch (IOException e) {
        }
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
