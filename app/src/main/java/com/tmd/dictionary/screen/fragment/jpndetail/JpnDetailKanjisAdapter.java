package com.tmd.dictionary.screen.fragment.jpndetail;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.databinding.ItemKanjiJpnDetailBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmd on 20/08/2017.
 */
public class JpnDetailKanjisAdapter
    extends RecyclerView.Adapter<JpnDetailKanjisAdapter.ViewHolder> {
    private JpnDetailContract.ViewModel mViewModel;
    private List<Kanji> mList = new ArrayList<>();

    public JpnDetailKanjisAdapter(JpnDetailContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    public JpnDetailKanjisAdapter(JpnDetailContract.ViewModel viewModel, List<Kanji> list) {
        mViewModel = viewModel;
        mList = list;
    }

    public void setSource(List<Kanji> lists) {
        mList.clear();
        if (lists == null) {
            return;
        }
        mList = lists;
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return mList.isEmpty();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemKanjiJpnDetailBinding binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_kanji_jpn_detail, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Kanji kanji = mList.get(position);
        holder.bind(kanji);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemKanjiJpnDetailBinding mBinding;

        public ViewHolder(ItemKanjiJpnDetailBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(Kanji kanji) {
            mBinding.setViewModel((JpnDetailViewModel) mViewModel);
            mBinding.setKanji(kanji);
        }
    }
}
