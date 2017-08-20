package com.tmd.dictionary.screen.fragment.search;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

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
    private static final long EXIT_DELAY = 2000;
    private SearchContract.ViewModel mViewModel;
    private boolean mIsDoubleClickedBack;

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

    @Override
    public void onBackPressed() {
        if (mIsDoubleClickedBack) {
            super.onBackPressed();
            return;
        }
        mIsDoubleClickedBack = true;
        Toast.makeText(this, getString(R.string.double_click_back), Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mIsDoubleClickedBack = false;
            }
        }, EXIT_DELAY);
    }
}
