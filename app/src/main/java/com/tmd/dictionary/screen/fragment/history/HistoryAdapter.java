package com.tmd.dictionary.screen.fragment.history;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.History;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.RealmInteger;
import com.tmd.dictionary.data.model.RealmString;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.databinding.ItemGrammarBinding;
import com.tmd.dictionary.databinding.ItemJpnVieBinding;
import com.tmd.dictionary.databinding.ItemKanjiBinding;
import com.tmd.dictionary.databinding.ItemVieJpnBinding;
import com.tmd.dictionary.staticfinal.CustomGson;

import io.realm.RealmList;

import static com.tmd.dictionary.staticfinal.ConstantValue.INT_GRAMMAR;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_JPN_WORD;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_KANJI;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_VIE_WORD;

/**
 * Created by tmd on 03/09/2017.
 */
public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private HistoryContract.ViewModel mViewModel;
    private Gson mGson;
    private RealmList<RealmString> mListKey = new RealmList<>();
    private RealmList<RealmInteger> mListType = new RealmList<>();

    public HistoryAdapter(HistoryContract.ViewModel viewModel) {
        mViewModel = viewModel;
        mGson = CustomGson.getGson();
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
    public int getItemViewType(int position) {
        switch (mListType.get(position).getValue()) {
            case INT_JPN_WORD:
                return INT_JPN_WORD;
            case INT_VIE_WORD:
                return INT_VIE_WORD;
            case INT_KANJI:
                return INT_KANJI;
            case INT_GRAMMAR:
                return INT_GRAMMAR;
            default:
                return 0;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case INT_JPN_WORD:
                ItemJpnVieBinding jpnVieBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_jpn_vie, parent, false);
                return new JpnViewHolder(jpnVieBinding);
            case INT_VIE_WORD:
                ItemVieJpnBinding vieJpnBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_vie_jpn, parent, false);
                return new VieViewHolder(vieJpnBinding);
            case INT_KANJI:
                ItemKanjiBinding kanjiBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_kanji, parent, false);
                return new KanjiViewHolder(kanjiBinding);
            case INT_GRAMMAR:
                ItemGrammarBinding grammarBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_grammar, parent, false);
                return new GrammarViewHolder(grammarBinding);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (mListType.get(position).getValue()) {
            case INT_JPN_WORD:
                ((JpnViewHolder) holder)
                    .bindData(mGson.fromJson(mListKey.get(position).getValue(), JpnWord.class));
                break;
            case INT_VIE_WORD:
                ((VieViewHolder) holder)
                    .bindData(mGson.fromJson(mListKey.get(position).getValue(), VieWord.class));
                break;
            case INT_KANJI:
                ((KanjiViewHolder) holder)
                    .bindData(mGson.fromJson(mListKey.get(position).getValue(), Kanji.class));
                break;
            case INT_GRAMMAR:
                ((GrammarViewHolder) holder)
                    .bindData(mGson.fromJson(mListKey.get(position).getValue(), Grammar.class));
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mListKey.size();
    }

    private class JpnViewHolder extends RecyclerView.ViewHolder {
        private ItemJpnVieBinding mBinding;

        public JpnViewHolder(ItemJpnVieBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindData(JpnWord word) {
            mBinding.setViewModel((HistoryViewModel) mViewModel);
            mBinding.setJpnWord(word);
        }
    }

    private class KanjiViewHolder extends RecyclerView.ViewHolder {
        private ItemKanjiBinding mBinding;

        public KanjiViewHolder(ItemKanjiBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindData(Kanji kanji) {
            mBinding.setViewModel((HistoryViewModel) mViewModel);
            mBinding.setKanji(kanji);
        }
    }

    private class GrammarViewHolder extends RecyclerView.ViewHolder {
        private ItemGrammarBinding mBinding;

        public GrammarViewHolder(ItemGrammarBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindData(Grammar grammar) {
            mBinding.setViewModel((HistoryViewModel) mViewModel);
            mBinding.setGrammar(grammar);
        }
    }

    private class VieViewHolder extends RecyclerView.ViewHolder {
        private ItemVieJpnBinding mBinding;

        public VieViewHolder(ItemVieJpnBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindData(VieWord word) {
            mBinding.setViewModel((HistoryViewModel) mViewModel);
            mBinding.setVieWord(word);
        }
    }
}
