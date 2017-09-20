package com.tmd.dictionary.util;

import android.databinding.BindingAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tmd.dictionary.R;
import com.tmd.dictionary.screen.fragment.search.SearchViewModel;
import com.tmd.dictionary.screen.fragment.viedetail.VieDetailViewModel;
import com.tmd.dictionary.staticfinal.StringHandling;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Cancellable;

import static com.tmd.dictionary.staticfinal.ConstantValue.MY_TAG;

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
                            Log.e(MY_TAG, "afterTextChanged: " + editText.getText().toString());
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
        Log.e(MY_TAG, "onSendToAllFragment: ");
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

    @BindingAdapter({"viewModel", "linksText"})
    public static void linksText(final TextView textView, final VieDetailViewModel viewModel,
                                 String definition) {
        SpannableString spannableString = new SpannableString(definition);
        List<String> tokens = StringHandling.japaneseFilter(definition);
        int begin, end = 0;
        for (final String token : tokens) {
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View view) {
                    viewModel.onOpenSearchFragment(token);
                }
            };
            begin = definition.indexOf(token, end);
            end = begin + token.length();
            spannableString.setSpan(clickableSpan, begin, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
