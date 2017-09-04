package com.tmd.dictionary.util;

import android.app.Application;
import android.content.Context;

import com.tmd.dictionary.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.tmd.dictionary.staticfinal.ConstantValue.SCHEMA_VERSION;

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
        RealmConfiguration config = new RealmConfiguration.Builder()
            .schemaVersion(SCHEMA_VERSION)
            .assetFile(DictApplication.getContext().getString(R.string.database_name))
            .build();
        Realm.setDefaultConfiguration(config);
    }

    public static Context getContext() {
        return sInstance;
    }
}
