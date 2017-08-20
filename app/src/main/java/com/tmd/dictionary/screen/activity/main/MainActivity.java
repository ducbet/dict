package com.tmd.dictionary.screen.activity.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.source.local.DatabaseHelper;
import com.tmd.dictionary.databinding.ActivityMainBinding;
import com.tmd.dictionary.screen.BaseActivity;

import java.io.IOException;

/**
 * Main Screen.
 */
public class MainActivity extends BaseActivity {
    private static final long EXIT_DELAY = 2000;
    private MainContract.ViewModel mViewModel;
    private boolean mIsDoubleClickedBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new MainViewModel(this);
        MainContract.Presenter presenter =
            new MainPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        ActivityMainBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel((MainViewModel) mViewModel);
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
        if (!mViewModel.isSearchFragVisibility()) {
            super.onBackPressed();
            return;
        }
        if (mIsDoubleClickedBack) {
            finish();
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
