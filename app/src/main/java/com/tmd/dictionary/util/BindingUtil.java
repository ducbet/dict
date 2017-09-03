package com.tmd.dictionary.util;

import android.databinding.BindingAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import com.tmd.dictionary.R;
import com.tmd.dictionary.screen.fragment.search.SearchViewModel;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Cancellable;

/**
 * Created by tmd on 15/08/2017.
 */
public class BindingUtil {
    @BindingAdapter("setViewPager")
    public static void setTabIcon(TabLayout tabLayout, ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
    }

    @BindingAdapter("setPagerAdapter")
    public static void setPagerAdapter(ViewPager viewPager, FragmentPagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView,
                                        LayoutManagers.LayoutManagerFactory layoutManagerFactory) {
        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }

    @BindingAdapter("addTextWatcher")
    public static void addTextWatcher(final EditText editText, SearchViewModel viewModel) {
        Observable<String> textChangeObservable = Observable.create(
            new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(@NonNull final ObservableEmitter<String> e) throws Exception {
                    final TextWatcher textWatcher = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence,
                                                      int i, int i1, int i2) {
                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence,
                                                  int i, int i1, int i2) {
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            e.onNext(editText.getText().toString());
                        }
                    };
                    editText.addTextChangedListener(textWatcher);
                    e.setCancellable(new Cancellable() {
                        @Override
                        public void cancel() throws Exception {
                            editText.removeTextChangedListener(textWatcher);
                        }
                    });
                }
            });
        viewModel.onSendToAllFragment(textChangeObservable);
    }

    @BindingAdapter("src")
    public static void setLoveImgSource(final ImageView imageView, boolean isLiked) {
        if (isLiked) {
            imageView.setImageResource(R.drawable.ic_heart_fill);
            return;
        }
        imageView.setImageResource(R.drawable.ic_heart_unfill);
    }
}
