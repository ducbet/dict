package com.tmd.dictionary.screen.fragment.search.level2.kanji;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.databinding.ItemKanjiBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmd on 18/08/2017.
 */
public class KanjiAdapter extends RecyclerView.Adapter<KanjiAdapter.ViewHolder> {
    private KanjiContract.ViewModel mViewModel;
    private List<Kanji> mList = new ArrayList<>();

    public KanjiAdapter(KanjiContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void setSource(List<Kanji> list) {
        if (list == null) {
            return;
        }
        mList = list;
        notifyDataSetChanged();
    }

    public void setSource(Kanji kanji) {
        mList.add(kanji);
        notifyItemInserted(mList.size() - 1);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemKanjiBinding binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_kanji, parent, false);
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
        private ItemKanjiBinding mBinding;

        public ViewHolder(ItemKanjiBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(Kanji kanji) {
            mBinding.setViewModel((KanjiViewModel) mViewModel);
            mBinding.setKanji(kanji);
        }
    }
}
