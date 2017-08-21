package com.tmd.dictionary.data.source.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.staticfinal.StringHandling;

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

    public Observable<Word> searchJpnVie(final String input) {
        // SELECT * FROM jpn_vie_main WHERE c0origin LIKE ?
        // OR c1kana LIKE ? ORDER BY c3priority DESC LIMIT 100
        return Observable.create(new ObservableOnSubscribe<Word>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Word> e) throws Exception {
                SQLiteDatabase database = getReadableDatabase();
                String selection =
                    DatabaseContract.JpnVieContract.Main.COLUMN_ORIGIN + " LIKE ? OR " +
                        DatabaseContract.JpnVieContract.Main.COLUMN_KANA + " LIKE ?";
                String[] selectionArgs = new String[]{"%" + input + "%", "%" + input + "%"};
                String limit = mContext.getString(R.string.result_limit);
                Cursor cursor = database.query(
                    DatabaseContract.JpnVieContract.Main.TABLE_NAME,
                    null,// columns// *
                    selection,
                    selectionArgs,
                    null,// group by
                    null,// group by args
                    DatabaseContract.JpnVieContract.Main.COLUMN_PRIORITY + " DESC",
                    limit);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        Word word = new Word();
                        int id = cursor.getInt(cursor.getColumnIndex(
                            DatabaseContract.JpnVieContract.Main.COLUMN_DOC_ID));
                        String origin = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.JpnVieContract.Main.COLUMN_ORIGIN));
                        String kana = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.JpnVieContract.Main.COLUMN_KANA));
                        String definition = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.JpnVieContract.Main.COLUMN_DEFINITION));
                        int priority = cursor.getInt(cursor.getColumnIndex(
                            DatabaseContract.JpnVieContract.Main.COLUMN_PRIORITY));
                        word.setId(id);
                        word.setOrigin(origin);
                        word.setKana(kana);
                        word.setDefinition(definition);
                        word.setPriority(priority);
                        StringHandling.formatWord(word);
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

    public Observable<Word> searchVieJpn(final String input) {
        return Observable.create(new ObservableOnSubscribe<Word>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Word> e) throws Exception {
                SQLiteDatabase database = getReadableDatabase();
                String selection =
                    DatabaseContract.VieJpnContract.Main.COLUMN_ORIGIN +
                        " LIKE ? COLLATE NOCASE OR " +
                        DatabaseContract.VieJpnContract.Main.COLUMN_KANA + " LIKE ? COLLATE NOCASE";
                String[] selectionArgs = new String[]{"%" + input + "%", "%" + input + "%"};
                String limit = mContext.getString(R.string.result_limit);
                Cursor cursor = database.query(
                    DatabaseContract.VieJpnContract.Main.TABLE_NAME,
                    null,// columns// *
                    selection,
                    selectionArgs,
                    null,// group by
                    null,// group by args
                    null,// order by
                    limit);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        Word word = new Word();
                        String origin = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.VieJpnContract.Main.COLUMN_ORIGIN));
                        String kana = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.VieJpnContract.Main.COLUMN_KANA));
                        String definition = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.VieJpnContract.Main.COLUMN_DEFINITION));
                        word.setOrigin(origin);
                        word.setKana(kana);
                        word.setDefinition(definition);
                        StringHandling.formatWord(word);
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

    public Observable<List<Kanji>> searchKanji(final String input) {
        // SELECT * FROM kanji_main WHERE kanji = ?
        return Observable.create(new ObservableOnSubscribe<List<Kanji>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Kanji>> e) throws Exception {
                SQLiteDatabase database = getReadableDatabase();
                List<Kanji> listKanjis = new ArrayList<>();
                String selection = DatabaseContract.KanjiContract.Main.COLUMN_KANJI + " = ?";
                String[] selectionArgs = new String[]{input};
                String limit = mContext.getString(R.string.result_limit);
                Cursor cursor = database.query(
                    DatabaseContract.KanjiContract.Main.TABLE_NAME,
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
                            DatabaseContract.KanjiContract.Main.COLUMN_KANJI));
                        String hanviet = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.KanjiContract.Main.COLUMN_HANVIET));
                        String onyomi = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.KanjiContract.Main.COLUMN_ONYOMI));
                        String kunyomi = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.KanjiContract.Main.COLUMN_KUNYOMI));
                        String meaning = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.KanjiContract.Main.COLUMN_MEANING));
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

    public Observable<Word> searchGrammar(final String input) {
        return Observable.create(new ObservableOnSubscribe<Word>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Word> e) throws Exception {
                SQLiteDatabase database = getReadableDatabase();
                String selection = DatabaseContract.GrammarContract.Main.COLUMN_ORIGIN + " LIKE ?";
                String[] selectionArgs = new String[]{"%" + input + "%"};
                String limit = mContext.getString(R.string.result_limit);
                Cursor cursor = database.query(
                    DatabaseContract.GrammarContract.Main.TABLE_NAME,
                    null,// columns// *
                    selection,
                    selectionArgs,
                    null,// group by
                    null,// group by args
                    null,// order by
                    limit);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        Word word = new Word();
                        String origin = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.GrammarContract.Main.COLUMN_ORIGIN));
                        String definition = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.GrammarContract.Main.COLUMN_DEFINITION));
                        word.setOrigin(origin);
                        word.setDefinition(definition);
                        StringHandling.formatWord(word);
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
}
