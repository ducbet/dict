package com.tmd.dictionary.data.source.local;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmd on 09/07/2017.
 */
public class _CRUDHelper extends DatabaseHelper {
    public _CRUDHelper(Context context) {
        super(context);
    }

    public List<Word> JpnVieDefinition(String input) {
        // SELECT c0origin, c1kana, c2definition FROM fts_main_content
        // WHERE c0origin LIKE '%食%' ORDER BY c3priority DESC
        SQLiteDatabase database = getReadableDatabase();
        List<Word> response = new ArrayList<>();
        String[] selectionArgs = new String[]{"%" + input + "%"};
        Cursor cursor =
            database.rawQuery(mContext.getString(R.string.query_jpn_vie_definition), selectionArgs);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Word word = new Word();
                String origin = cursor.getString(
                    cursor.getColumnIndex(JpnVieContract.MainContent.COLUMN_ORIGIN));
                String kana = cursor.getString(
                    cursor.getColumnIndex(JpnVieContract.MainContent.COLUMN_KANA));
                String definition = cursor.getString(
                    cursor.getColumnIndex(JpnVieContract.MainContent.COLUMN_DEFINITION));
                int priority = cursor.getInt(
                    cursor.getColumnIndex(JpnVieContract.MainContent.COLUMN_PRIORITY));
                word.setOrigin(origin);
                word.setKana(kana);
                word.setDefinition(definition);
                word.setPriority(priority);
                response.add(word);
            }
            cursor.close();
        }
        database.close();
        return response;
    }
}
