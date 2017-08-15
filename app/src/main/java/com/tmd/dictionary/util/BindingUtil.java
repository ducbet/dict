package com.tmd.dictionary.util;

import android.databinding.BindingAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.tmd.dictionary.screen.activity.search.SearchActivity;
import com.tmd.dictionary.screen.activity.search.SearchPagerAdapter;
import com.tmd.dictionary.screen.activity.search.SearchViewModel;

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

}
