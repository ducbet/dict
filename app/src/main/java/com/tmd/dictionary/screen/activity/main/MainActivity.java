package com.tmd.dictionary.screen.activity.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.tmd.dictionary.R;
import com.tmd.dictionary.databinding.ActivityMainBinding;
import com.tmd.dictionary.databinding.NavigationHeaderBinding;
import com.tmd.dictionary.screen.BaseActivity;

import java.io.Serializable;

import io.realm.Realm;

/**
 * Main Screen.
 */
public class MainActivity extends BaseActivity implements Serializable {
    private static final long EXIT_DELAY = 2000;
    private MainContract.ViewModel mViewModel;
    private boolean mIsDoubleClickedBack;
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRealm = Realm.getDefaultInstance();
        mViewModel = new MainViewModel(this, mRealm);
        MainContract.Presenter presenter = new MainPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        ActivityMainBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel((MainViewModel) mViewModel);
        createNavigationView(binding);
    }

    private void createNavigationView(ActivityMainBinding binding) {
        NavigationHeaderBinding headerBinding = DataBindingUtil.inflate(
            getLayoutInflater(), R.layout.navigation_header, binding.leftDrawer, false);
        headerBinding.setViewModel((MainViewModel) mViewModel);
        ((MainViewModel) mViewModel).setNavigationView(binding.drawerLayout, binding.leftDrawer);
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
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
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
