package com.tmd.dictionary.data.source.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.staticfinal.ReformatString;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by tmd on 09/07/2017.
 */
public class _CRUDHelper extends DatabaseHelper {
    public _CRUDHelper(Context context) {
        super(context);
    }

    public Observable<Word> searchJpnVieDefinition(final String input) {
        return Observable.create(new ObservableOnSubscribe<Word>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Word> e) throws Exception {
                SQLiteDatabase database = getReadableDatabase();
                String[] selectionArgs = new String[]{"%" + input + "%", "%" + input + "%"};
                Cursor cursor =
                    database.rawQuery(mContext.getString(R.string.query_jpn_vie_definition),
                        selectionArgs);
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
                        ReformatString.formatWord(word);
                        e.onNext(word);
                    }
                    cursor.close();
                }
                database.close();
                e.onComplete();
            }
        });
    }

    /*



     */

    public List<Kanji> searchKanji(String input) {
        // SELECT * FROM fts_main_content
        // WHERE c0origin LIKE '%食%' ORDER BY c3priority DESC
        SQLiteDatabase database = getReadableDatabase();
        List<Kanji> response = new ArrayList<>();
        database.close();
        return response;
    }
}
