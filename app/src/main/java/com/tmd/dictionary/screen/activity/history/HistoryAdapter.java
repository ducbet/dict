package com.tmd.dictionary.screen.activity.history;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.History;
import com.tmd.dictionary.data.model.RealmInteger;
import com.tmd.dictionary.data.model.RealmString;
import com.tmd.dictionary.databinding.ItemHistoryBinding;

import io.realm.RealmList;

/**
 * Created by tmd on 03/09/2017.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private HistoryContract.ViewModel mViewModel;
    private RealmList<RealmString> mListKey = new RealmList<>();
    private RealmList<RealmInteger> mListType = new RealmList<>();

    public HistoryAdapter(HistoryContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void setSource(History history) {
        if (history == null) {
            return;
        }
        mListKey = history.getPrimaryKey();
        mListType = history.getType();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemHistoryBinding binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_history, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String key = mListKey.get(position).getValue();
        Integer type = mListType.get(position).getValue();
        holder.bind(key, type);
    }

    @Override
    public int getItemCount() {
        return mListKey.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemHistoryBinding mBinding;

        public ViewHolder(ItemHistoryBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(String string, Integer type) {
            mBinding.setViewModel((HistoryViewModel) mViewModel);
            mBinding.setKey(string);
            mBinding.setType(type);
        }
    }
}
