package com.tmd.dictionary.util;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by tmd on 22/08/2017.
 */
public class DictApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
