package com.tmd.dictionary.screen.activity.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.tmd.dictionary.R;
import com.tmd.dictionary.databinding.ActivityMainBinding;
import com.tmd.dictionary.screen.BaseActivity;

/**
 * Main Screen.
 */
public class MainActivity extends BaseActivity {
    private MainContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new MainViewModel();
        MainContract.Presenter presenter =
            new MainPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        ActivityMainBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel((MainViewModel) mViewModel);
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
