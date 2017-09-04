package com.tmd.dictionary.screen.fragment.search.level2.javvie;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.databinding.ItemJpnVieBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmd on 18/08/2017.
 */
public class JavVieAdapter extends RecyclerView.Adapter<JavVieAdapter.ViewHolder> {
    private JavVieContract.ViewModel mViewModel;
    private List<JpnWord> mList = new ArrayList<>();

    public JavVieAdapter(JavVieContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void setSource(List<JpnWord> list) {
        if (list == null) {
            mList = new ArrayList<>();
            notifyDataSetChanged();
            return;
        }
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemJpnVieBinding binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_jpn_vie, parent, false);
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
        private ItemJpnVieBinding mBinding;

        public ViewHolder(ItemJpnVieBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(JpnWord jpnWord) {
            mBinding.setViewModel((JavVieViewModel) mViewModel);
            mBinding.setJpnWord(jpnWord);
        }
    }
}
