package com.tmd.dictionary.screen.activity.history;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.source.Repository;
import com.tmd.dictionary.data.source.local.LocalDataSource;
import com.tmd.dictionary.databinding.ActivityHistoryBinding;
import com.tmd.dictionary.screen.BaseActivity;

/**
 * History Screen.
 */
public class HistoryActivity extends BaseActivity {
    private HistoryContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new HistoryViewModel(this);
        HistoryContract.Presenter presenter =
            new HistoryPresenter(mViewModel, new Repository(new LocalDataSource(this)));
        mViewModel.setPresenter(presenter);
        ActivityHistoryBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_history);
        binding.setViewModel((HistoryViewModel) mViewModel);
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
