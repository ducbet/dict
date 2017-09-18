package com.tmd.dictionary.screen.fragment.movetobox;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.GrammarBox;
import com.tmd.dictionary.data.model.JpnBox;
import com.tmd.dictionary.data.model.KanjiBox;
import com.tmd.dictionary.data.model.VieBox;
import com.tmd.dictionary.databinding.ItemBoxBinding;

import java.util.ArrayList;
import java.util.List;

import static com.tmd.dictionary.staticfinal.ConstantValue.INT_GRAMMAR;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_JPN_WORD;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_KANJI;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_VIE_WORD;

/**
 * Created by tmd on 19/09/2017.
 */
public class BoxAdapter extends RecyclerView.Adapter<BoxAdapter.ViewHolder> {
    private MoveToBoxViewModel mViewModel;
    private int mBoxType = INT_JPN_WORD;
    private List mList = new ArrayList<>();

    public BoxAdapter(MoveToBoxViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void setSource(List list) {
        mList = list;
        notifyDataSetChanged();
        getListType(list);
    }

    private void getListType(List list) {
        if (list instanceof JpnBox) {
            mBoxType = INT_JPN_WORD;
            return;
        }
        if (list instanceof VieBox) {
            mBoxType = INT_VIE_WORD;
            return;
        }
        if (list instanceof KanjiBox) {
            mBoxType = INT_KANJI;
            return;
        }
        if (list instanceof GrammarBox) {
            mBoxType = INT_GRAMMAR;
            return;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mBoxType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case INT_JPN_WORD:
                ItemBoxBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_box, parent, false);
                return new ViewHolder(binding);
            case INT_VIE_WORD:
            case INT_KANJI:
            case INT_GRAMMAR:
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case INT_JPN_WORD:
                JpnBox box = (JpnBox) mList.get(position);
                holder.bind(box);
                break;
            case INT_VIE_WORD:
                break;
            case INT_KANJI:
                break;
            case INT_GRAMMAR:
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemBoxBinding mBinding;

        public ViewHolder(ItemBoxBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(JpnBox box) {
            mBinding.setViewModel(mViewModel);
            mBinding.setBox(box);
        }
    }
}

