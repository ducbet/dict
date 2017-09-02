package com.tmd.dictionary.screen.activity.boxs;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.tmd.dictionary.R;
import com.tmd.dictionary.databinding.ActivityBoxesBinding;
import com.tmd.dictionary.screen.BaseActivity;

/**
 * FlashCardBoxs Screen.
 */
public class BoxesActivity extends BaseActivity {
    private BoxesContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new BoxesViewModel();
        BoxesContract.Presenter presenter = new BoxesPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        ActivityBoxesBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_boxes);
        binding.setViewModel((BoxesViewModel) mViewModel);
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
