package com.tmd.dictionary.util;

import android.databinding.BindingAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import com.tmd.dictionary.screen.fragment.search.SearchPagerAdapter;

/**
 * Created by tmd on 15/08/2017.
 */
public class BindingUtil {
    @BindingAdapter("setViewPager")
    public static void setTabIcon(TabLayout tabLayout, ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
    }

    @BindingAdapter("setPagerAdapter")
    public static void setPagerAdapter(ViewPager viewPager, SearchPagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView,
                                        LayoutManagers.LayoutManagerFactory layoutManagerFactory) {
        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }
}
