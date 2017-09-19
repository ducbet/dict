package com.tmd.dictionary.screen.fragment.movetobox;

import android.app.Dialog;
import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.tmd.dictionary.R;
import com.tmd.dictionary.databinding.FragmentMoveToBoxBinding;
import com.tmd.dictionary.screen.OpenableMoveToBoxFrag;

import static com.tmd.dictionary.staticfinal.ConstantValue.BUNDLE_VIEW_MODEL;

/**
 * Created by tmd on 18/09/2017.
 */
public class MoveToBoxFragment extends DialogFragment {
    private OpenableMoveToBoxFrag mWordDetailViewModel;
    private MoveToBoxViewModel mViewModel;

    public static MoveToBoxFragment newInstance(OpenableMoveToBoxFrag viewModel) {
        MoveToBoxFragment moveToBoxFragment = new MoveToBoxFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_VIEW_MODEL, viewModel);
        moveToBoxFragment.setArguments(bundle);
        return moveToBoxFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
            return;
        }
        mWordDetailViewModel = getArguments().getParcelable(BUNDLE_VIEW_MODEL);
        mViewModel = new MoveToBoxViewModel(mWordDetailViewModel);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMoveToBoxBinding binding = DataBindingUtil.inflate(inflater, R.layout
            .fragment_move_to_box, container, false);
        binding.setViewModel(mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewModel.onStop();
    }
}
