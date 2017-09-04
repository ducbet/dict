package com.tmd.dictionary.screen.fragment.search.level2.viejav;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.databinding.ItemVieJpnBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmd on 18/08/2017.
 */
public class VieJpnAdapter extends RecyclerView.Adapter<VieJpnAdapter.ViewHolder> {
    private VieJavContract.ViewModel mViewModel;
    private List<VieWord> mList = new ArrayList<>();

    public VieJpnAdapter(VieJavContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void setSource(List<VieWord> list) {
        if (list == null) {
            return;
        }
        mList = list;
        notifyDataSetChanged();
    }

    public void setSource(VieWord vieWord) {
        mList.add(vieWord);
        notifyItemInserted(mList.size() - 1);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemVieJpnBinding binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_vie_jpn, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VieWord vieWord = mList.get(position);
        holder.bind(vieWord);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemVieJpnBinding mBinding;

        public ViewHolder(ItemVieJpnBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(VieWord vieWord) {
            mBinding.setViewModel((VieJavViewModel) mViewModel);
            mBinding.setVieWord(vieWord);
        }
    }
}
