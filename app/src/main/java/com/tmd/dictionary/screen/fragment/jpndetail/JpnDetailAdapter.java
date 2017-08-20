package com.tmd.dictionary.screen.fragment.jpndetail;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.viewmodel.ExamplesShowViewModel;
import com.tmd.dictionary.data.viewmodel.KanaShowViewModel;
import com.tmd.dictionary.data.viewmodel.KanjisShowViewModel;
import com.tmd.dictionary.data.viewmodel.OriginShowViewModel;
import com.tmd.dictionary.databinding.ItemExamplesShowComponentBinding;
import com.tmd.dictionary.databinding.ItemKanaShowComponentBinding;
import com.tmd.dictionary.databinding.ItemOriginShowComponentBinding;

import java.util.ArrayList;
import java.util.List;

import static com.tmd.dictionary.staticfinal.ConstantValue.ITEM_EMPTY_COMPONENT;
import static com.tmd.dictionary.staticfinal.ConstantValue.ITEM_EXAMPLES_COMPONENT;
import static com.tmd.dictionary.staticfinal.ConstantValue.ITEM_KANA_COMPONENT;
import static com.tmd.dictionary.staticfinal.ConstantValue.ITEM_KANJIS_COMPONENT;
import static com.tmd.dictionary.staticfinal.ConstantValue.ITEM_ORIGIN_COMPONENT;

/**
 * Created by tmd on 18/08/2017.
 */
public class JpnDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private JpnDetailContract.ViewModel mViewModel;
    private List mListComponents = new ArrayList<>();

    public JpnDetailAdapter(JpnDetailContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void setSource(List listComponents) {
        if (listComponents == null) {
            return;
        }
        mListComponents = listComponents;
        notifyDataSetChanged();
    }

    public void setSource(Object component) {
        mListComponents.add(component);
        notifyItemInserted(mListComponents.size() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (mListComponents.get(position) instanceof OriginShowViewModel) {
            return ITEM_ORIGIN_COMPONENT;
        }
        if (mListComponents.get(position) instanceof KanaShowViewModel) {
            return ITEM_KANA_COMPONENT;
        }
        if (mListComponents.get(position) instanceof KanaShowViewModel) {
            return ITEM_KANJIS_COMPONENT;
        }
        if (mListComponents.get(position) instanceof ExamplesShowViewModel) {
            return ITEM_EXAMPLES_COMPONENT;
        }
        return ITEM_EMPTY_COMPONENT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ITEM_ORIGIN_COMPONENT:
                ItemOriginShowComponentBinding originBinding =
                    DataBindingUtil.inflate(layoutInflater,
                        R.layout.item_origin_show_component, parent, false);
                return new OriginViewHolder(originBinding);
            case ITEM_KANA_COMPONENT:
                ItemKanaShowComponentBinding kanaBinding =
                    DataBindingUtil.inflate(layoutInflater,
                        R.layout.item_kana_show_component, parent, false);
                return new KanaViewHolder(kanaBinding);
            case ITEM_KANJIS_COMPONENT:
                ItemKanaShowComponentBinding kanjisBinding =
                    DataBindingUtil.inflate(layoutInflater,
                        R.layout.item_kanjis_show_component, parent, false);
                return new KanjisViewHolder(kanjisBinding);
            case ITEM_EXAMPLES_COMPONENT:
                ItemExamplesShowComponentBinding examplesBinding =
                    DataBindingUtil.inflate(layoutInflater,
                        R.layout.item_examples_show_component, parent, false);
                return new ExamplesViewHolder(examplesBinding);
            default:
                Toast.makeText(parent.getContext(),
                    parent.getContext().getString(R.string.unknown_component), Toast.LENGTH_SHORT)
                    .show();
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case ITEM_ORIGIN_COMPONENT:// ItemNewFeed
                ((OriginViewHolder) holder)
                    .blindData((OriginShowViewModel) mListComponents.get(position));
                break;
            case ITEM_KANA_COMPONENT:// ItemFriendSuggest
                ((KanaViewHolder) holder)
                    .blindData((KanaShowViewModel) mListComponents.get(position));
                break;
            case ITEM_KANJIS_COMPONENT:// ItemPostStatus
                ((KanjisViewHolder) holder)
                    .blindData((KanjisShowViewModel) mListComponents.get(position));
                break;
            case ITEM_EXAMPLES_COMPONENT:
                ((ExamplesViewHolder) holder)
                    .blindData((ExamplesShowViewModel) mListComponents.get(position));
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mListComponents == null ? 0 : mListComponents.size();
    }

    public class OriginViewHolder extends RecyclerView.ViewHolder {
        private ItemOriginShowComponentBinding mBinding;

        public OriginViewHolder(ItemOriginShowComponentBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void blindData(OriginShowViewModel component) {
            mBinding.setViewModel(component);
        }
    }

    public class KanaViewHolder extends RecyclerView.ViewHolder {
        private ItemKanaShowComponentBinding mBinding;

        public KanaViewHolder(ItemKanaShowComponentBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void blindData(KanaShowViewModel component) {
        }
    }

    public class KanjisViewHolder extends RecyclerView.ViewHolder {
        private ItemKanaShowComponentBinding mBinding;

        public KanjisViewHolder(ItemKanaShowComponentBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void blindData(KanjisShowViewModel component) {
        }
    }

    public class ExamplesViewHolder extends RecyclerView.ViewHolder {
        private ItemExamplesShowComponentBinding mBinding;

        public ExamplesViewHolder(ItemExamplesShowComponentBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void blindData(ExamplesShowViewModel component) {
        }
    }
}
