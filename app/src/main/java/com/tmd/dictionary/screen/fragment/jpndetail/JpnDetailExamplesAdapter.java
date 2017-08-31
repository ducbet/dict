package com.tmd.dictionary.screen.fragment.jpndetail;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.RealmString;
import com.tmd.dictionary.databinding.ItemExampleJpnDetailBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmd on 20/08/2017.
 */
public class JpnDetailExamplesAdapter
    extends RecyclerView.Adapter<JpnDetailExamplesAdapter.ViewHolder> {
    private JpnDetailContract.ViewModel mViewModel;
    private List<RealmString> mList = new ArrayList<>();

    public JpnDetailExamplesAdapter(JpnDetailContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    public JpnDetailExamplesAdapter(JpnDetailContract.ViewModel viewModel,
                                    List<RealmString> list) {
        mViewModel = viewModel;
        mList = list;
    }

    public void setSource(List<RealmString> lists) {
        mList.clear();
        if (lists == null) {
            return;
        }
        mList = lists;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemExampleJpnDetailBinding binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_example_jpn_detail, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RealmString example = mList.get(position);
        holder.bind(example);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemExampleJpnDetailBinding mBinding;

        public ViewHolder(ItemExampleJpnDetailBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(RealmString example) {
            mBinding.setViewModel((JpnDetailViewModel) mViewModel);
            mBinding.setRealmString(example);
        }
    }
}
