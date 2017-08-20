package com.tmd.dictionary.screen.fragment.level2.grammar;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.databinding.ItemGrammarBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmd on 18/08/2017.
 */
public class GrammarAdapter extends RecyclerView.Adapter<GrammarAdapter.ViewHolder> {
    private GrammarContract.ViewModel mViewModel;
    private List<Word> mList = new ArrayList<>();

    public GrammarAdapter(GrammarContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void setSource(List<Word> list) {
        if (list == null) {
            return;
        }
        mList = list;
        notifyDataSetChanged();
    }

    public void setSource(Word word) {
        mList.add(word);
        notifyItemInserted(mList.size() - 1);
    }

    public void clearData() {
        mList.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGrammarBinding binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_grammar, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Word word = mList.get(position);
        holder.bind(word);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemGrammarBinding mBinding;

        public ViewHolder(ItemGrammarBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(Word word) {
            mBinding.setViewModel((GrammarViewModel) mViewModel);
            mBinding.setWord(word);
        }
    }
}
