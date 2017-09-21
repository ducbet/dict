package com.tmd.dictionary.screen.fragment.listboxes;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.GrammarBox;
import com.tmd.dictionary.data.model.JpnBox;
import com.tmd.dictionary.data.model.KanjiBox;
import com.tmd.dictionary.data.model.VieBox;
import com.tmd.dictionary.databinding.ItemGrammarBoxDetailBinding;
import com.tmd.dictionary.databinding.ItemJpnBoxDetailBinding;
import com.tmd.dictionary.databinding.ItemKanjiBoxDetailBinding;
import com.tmd.dictionary.databinding.ItemVieBoxDetailBinding;

import java.util.ArrayList;
import java.util.List;

import static com.tmd.dictionary.staticfinal.ConstantValue.INT_GRAMMAR;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_JPN_WORD;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_KANJI;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_VIE_WORD;

/**
 * Created by tmd on 21/09/2017.
 */
public class ListBoxesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ListBoxesContract.ViewModel mViewModel;
    private int mBoxType = INT_JPN_WORD;
    private List mList = new ArrayList<>();

    public ListBoxesAdapter(ListBoxesContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void setSource(List list) {
        mList = list;
        notifyDataSetChanged();
        getListType(list);
    }

    private void getListType(List list) {
        if (list.isEmpty()) {
            return;
        }
        if (list.get(0) instanceof JpnBox) {
            mBoxType = INT_JPN_WORD;
            return;
        }
        if (list.get(0) instanceof VieBox) {
            mBoxType = INT_VIE_WORD;
            return;
        }
        if (list.get(0) instanceof KanjiBox) {
            mBoxType = INT_KANJI;
            return;
        }
        if (list.get(0) instanceof GrammarBox) {
            mBoxType = INT_GRAMMAR;
            return;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mBoxType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case INT_JPN_WORD:
                ItemJpnBoxDetailBinding jpnBoxBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_jpn_box_detail, parent, false);
                return new JpnBoxViewHolder(jpnBoxBinding);
            case INT_VIE_WORD:
                ItemVieBoxDetailBinding vieBoxBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_vie_box_detail, parent, false);
                return new VieBoxViewHolder(vieBoxBinding);
            case INT_KANJI:
                ItemKanjiBoxDetailBinding kanjiBoxBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_kanji_box_detail, parent, false);
                return new KanjiBoxViewHolder(kanjiBoxBinding);
            case INT_GRAMMAR:
                ItemGrammarBoxDetailBinding grammarBoxBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_grammar_box_detail, parent, false);
                return new GrammarBoxViewHolder(grammarBoxBinding);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case INT_JPN_WORD:
                JpnBox jpnBox = (JpnBox) mList.get(position);
                ((JpnBoxViewHolder) holder).bind(jpnBox);
                break;
            case INT_VIE_WORD:
                VieBox vieBox = (VieBox) mList.get(position);
                ((VieBoxViewHolder) holder).bind(vieBox);
                break;
            case INT_KANJI:
                KanjiBox kanjiBox = (KanjiBox) mList.get(position);
                ((KanjiBoxViewHolder) holder).bind(kanjiBox);
                break;
            case INT_GRAMMAR:
                GrammarBox grammarBox = (GrammarBox) mList.get(position);
                ((GrammarBoxViewHolder) holder).bind(grammarBox);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class JpnBoxViewHolder extends RecyclerView.ViewHolder {
        private ItemJpnBoxDetailBinding mBinding;

        public JpnBoxViewHolder(ItemJpnBoxDetailBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(JpnBox box) {
            mBinding.setViewModel((ListBoxesViewModel) mViewModel);
            mBinding.setBox(box);
        }
    }

    public class VieBoxViewHolder extends RecyclerView.ViewHolder {
        private ItemVieBoxDetailBinding mBinding;

        public VieBoxViewHolder(ItemVieBoxDetailBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(VieBox box) {
            mBinding.setViewModel((ListBoxesViewModel) mViewModel);
            mBinding.setBox(box);
        }
    }

    public class KanjiBoxViewHolder extends RecyclerView.ViewHolder {
        private ItemKanjiBoxDetailBinding mBinding;

        public KanjiBoxViewHolder(ItemKanjiBoxDetailBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(KanjiBox box) {
            mBinding.setViewModel((ListBoxesViewModel) mViewModel);
            mBinding.setBox(box);
        }
    }

    public class GrammarBoxViewHolder extends RecyclerView.ViewHolder {
        private ItemGrammarBoxDetailBinding mBinding;

        public GrammarBoxViewHolder(ItemGrammarBoxDetailBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(GrammarBox box) {
            mBinding.setViewModel((ListBoxesViewModel) mViewModel);
            mBinding.setBox(box);
        }
    }
}

