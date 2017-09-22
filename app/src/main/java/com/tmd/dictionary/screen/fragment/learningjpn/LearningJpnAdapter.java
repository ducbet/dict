package com.tmd.dictionary.screen.fragment.learningjpn;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.JpnBox;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.databinding.ItemLearningJpnBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmd on 22/09/2017.
 */
public class LearningJpnAdapter extends RecyclerView.Adapter<LearningJpnAdapter.ViewHolder> {
    private LearningJpnContract.ViewModel mViewModel;
    private List<JpnWord> mList = new ArrayList<>();

    public LearningJpnAdapter(LearningJpnContract.ViewModel viewModel, JpnBox jpnBox) {
        mViewModel = viewModel;
        mList = jpnBox.getWords();
    }

    public void setSource(JpnBox jpnBox) {
        mList = jpnBox.getWords();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemLearningJpnBinding binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_learning_jpn, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JpnWord jpnWord = mList.get(position);
        holder.bind(jpnWord);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemLearningJpnBinding mBinding;

        public ViewHolder(ItemLearningJpnBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(JpnWord jpnWord) {
            mBinding.setViewModel((LearningJpnViewModel) mViewModel);
            mBinding.setJpnWord(jpnWord);
        }
    }
}
