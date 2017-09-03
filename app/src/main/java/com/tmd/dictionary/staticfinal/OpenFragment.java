package com.tmd.dictionary.staticfinal;

import android.support.v4.app.FragmentManager;

import com.tmd.dictionary.screen.BaseFragment;

/**
 * Created by tmd on 03/09/2017.
 */
public class OpenFragment {
    public static void openFragment(FragmentManager fragmentManager, int frameLayout,
                                    BaseFragment fragment) {
        fragmentManager
            .beginTransaction()
            .add(frameLayout, fragment)
            .addToBackStack(null)
            .commit();
    }
}
