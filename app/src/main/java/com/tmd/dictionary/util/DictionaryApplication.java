package com.tmd.dictionary.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by tmd on 15/08/2017.
 */
public class DictionaryApplication extends Application {
    private static DictionaryApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static Context getContext() {
        return mInstance;
    }
}
