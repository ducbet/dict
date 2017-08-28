package com.tmd.dictionary.util;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;

/**
 * Created by tmd on 22/08/2017.
 */
public class DictApplication extends Application {
    private static DictApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        if (sInstance == null) {
            sInstance = this;
        }
        Realm.init(this);
    }

    public static Context getContext() {
        return sInstance;
    }
}
