package com.tmd.dictionary.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by tmd on 09/07/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "jpn_vie.db";
    public static final String PACKAGE_NAME = "com.tmd.dictionary";
    public static final int DB_VERSION = 1;
    protected static Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
    }

    public static void copyDataBase(Context context) throws IOException {
        if (context.getDatabasePath(DB_NAME).exists()) {
            return;
        }
        File file = new File("/data/data/" + PACKAGE_NAME + "/databases");
        if (!file.exists())
            file.mkdir();
        File dbPath = context.getDatabasePath(DB_NAME);
        byte[] buffer = new byte[1024];
        int length;
        InputStream inputStream = context.getAssets().open(DB_NAME);
        OutputStream outputStream = new FileOutputStream(dbPath);
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        outputStream.close();
        outputStream.flush();
        inputStream.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
