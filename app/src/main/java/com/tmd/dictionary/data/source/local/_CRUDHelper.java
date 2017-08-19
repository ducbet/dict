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
        // SELECT * FROM jpn_vie_main WHERE c0origin LIKE ?
        // OR c1kana LIKE ? ORDER BY c3priority DESC LIMIT 100
        return Observable.create(new ObservableOnSubscribe<Word>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Word> e) throws Exception {
                SQLiteDatabase database = getReadableDatabase();
                String selection =
                    DatabaseContract.JpnVieContract.MainContent.COLUMN_ORIGIN + " LIKE ? OR " +
                        DatabaseContract.JpnVieContract.MainContent.COLUMN_KANA + " LIKE ?";
                String[] selectionArgs = new String[]{"%" + input + "%", "%" + input + "%"};
                String limit = mContext.getString(R.string.result_limit);
                Cursor cursor = database.query(
                    DatabaseContract.JpnVieContract.MainContent.TABLE_NAME,
                    null,// columns// *
                    selection,
                    selectionArgs,
                    null,// group by
                    null,// group by args
                    DatabaseContract.JpnVieContract.MainContent.COLUMN_PRIORITY + " DESC",
                    limit);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        Word word = new Word();
                        String origin = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.JpnVieContract.MainContent.COLUMN_ORIGIN));
                        String kana = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.JpnVieContract.MainContent.COLUMN_KANA));
                        String definition = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.JpnVieContract.MainContent.COLUMN_DEFINITION));
                        int priority = cursor.getInt(cursor.getColumnIndex(
                            DatabaseContract.JpnVieContract.MainContent.COLUMN_PRIORITY));
                        word.setOrigin(origin);
                        word.setKana(kana);
                        word.setDefinition(definition);
                        word.setPriority(priority);
                        ReformatString.formatWord(word);
                        e.onNext(word);
                    }
                    cursor.close();
                } else {
                    database.close();
                    e.onError(new NullPointerException(""));
                }
                database.close();
                e.onComplete();
            }
        });
    }

    public Observable<List<Kanji>> searchKanjiMeaning(final String input) {
        // SELECT * FROM kanji_main WHERE kanji = ?
        return Observable.create(new ObservableOnSubscribe<List<Kanji>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Kanji>> e) throws Exception {
                SQLiteDatabase database = getReadableDatabase();
                List<Kanji> listKanjis = new ArrayList<>();
                String selection = DatabaseContract.KanjiContract.KanjiBase.COLUMN_KANJI + " = ?";
                String[] selectionArgs = new String[]{input};
                String limit = mContext.getString(R.string.result_limit);
                Cursor cursor = database.query(
                    DatabaseContract.KanjiContract.KanjiBase.TABLE_NAME,
                    null,// columns// *
                    selection,
                    selectionArgs,
                    null,// group by
                    null,// group by args
                    null,// order by
                    limit);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        Kanji responseKanji = new Kanji();
                        String kanji = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.KanjiContract.KanjiBase.COLUMN_KANJI));
                        String hanviet = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.KanjiContract.KanjiBase.COLUMN_HANVIET));
                        String onyomi = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.KanjiContract.KanjiBase.COLUMN_ONYOMI));
                        String kunyomi = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.KanjiContract.KanjiBase.COLUMN_KUNYOMI));
                        String meaning = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.KanjiContract.KanjiBase.COLUMN_MEANING));
                        responseKanji.setKanji(kanji);
                        responseKanji.setHanViet(hanviet);
                        responseKanji.setOnyomi(onyomi);
                        responseKanji.setKunyomi(kunyomi);
                        responseKanji.setMeaning(meaning);
                        listKanjis.add(responseKanji);
                    }
                    cursor.close();
                } else {
                    database.close();
                    e.onError(new NullPointerException("cursor == null"));
                }
                database.close();
                e.onNext(listKanjis);
                e.onComplete();
            }
        });
    }
}
