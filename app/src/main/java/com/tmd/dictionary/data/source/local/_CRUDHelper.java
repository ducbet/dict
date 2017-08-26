package com.tmd.dictionary.data.source.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.VieWord;
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

    private SQLiteDatabase mDatabase;

    public Observable<JpnWord> searchJpnVie(final String input) {
        // SELECT * FROM jpn_vie_main WHERE c0origin LIKE ?
        // OR c1kana LIKE ? ORDER BY c3priority DESC LIMIT 100
        return Observable.create(new ObservableOnSubscribe<JpnWord>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<JpnWord> e) throws Exception {
                mDatabase = getReadableDatabase();
                String selection =
                    DatabaseContract.JpnVieContract.Main.COLUMN_ORIGIN + " LIKE ? OR " +
                        DatabaseContract.JpnVieContract.Main.COLUMN_KANA + " LIKE ?";
                String[] selectionArgs = new String[]{"%" + input + "%", "%" + input + "%"};
                String limit = mContext.getString(R.string.result_limit);
                Cursor cursor = mDatabase.query(
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
                        JpnWord jpnWord = new JpnWord();
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
//                        jpnWord.setId(id);
                        jpnWord.setOrigin(origin);
                        jpnWord.setKana(kana);
                        jpnWord.setDefinition(StringHandling.format(definition));
                        jpnWord.setPriority(priority);
                        e.onNext(jpnWord);
                    }
                    cursor.close();
                } else {
                    e.onError(new NullPointerException(""));
                }
                e.onComplete();
            }
        });
    }

    public Observable<VieWord> searchVieJpn(final String input) {
        return Observable.create(new ObservableOnSubscribe<VieWord>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<VieWord> e) throws Exception {
                mDatabase = getReadableDatabase();
                String selection =
                    DatabaseContract.VieJpnContract.Main.COLUMN_ORIGIN +
                        " LIKE ? COLLATE NOCASE OR " +
                        DatabaseContract.VieJpnContract.Main.COLUMN_KANA + " LIKE ? COLLATE NOCASE";
                String[] selectionArgs = new String[]{"%" + input + "%", "%" + input + "%"};
                String limit = mContext.getString(R.string.result_limit);
                Cursor cursor = mDatabase.query(
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
                        VieWord vieWord = new VieWord();
                        String origin = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.VieJpnContract.Main.COLUMN_ORIGIN));
                        String kana = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.VieJpnContract.Main.COLUMN_KANA));
                        String definition = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.VieJpnContract.Main.COLUMN_DEFINITION));
                        vieWord.setOrigin(origin);
                        vieWord.setKana(kana);
                        vieWord.setDefinition(StringHandling.format(definition));
                        e.onNext(vieWord);
                    }
                    cursor.close();
                } else {
                    e.onError(new NullPointerException(""));
                }
                e.onComplete();
            }
        });
    }

    public Observable<List<Kanji>> searchKanji(final String input) {
        // SELECT * FROM kanji_main WHERE kanji = ?
        return Observable.create(new ObservableOnSubscribe<List<Kanji>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Kanji>> e) throws Exception {
                mDatabase = getReadableDatabase();
                List<Kanji> listKanjis = new ArrayList<>();
                String selection = "";
                List<String> arrayListSelectionArgs = new ArrayList<>();
                for (int i = 0; i < input.length(); i++) {
                    selection += "OR " + DatabaseContract.KanjiContract.Main.COLUMN_KANJI + " = ? ";
                    arrayListSelectionArgs.add(String.valueOf(input.charAt(i)));
                }
                selection = selection.replaceFirst("OR ", "");
                String[] selectionArgs = new String[arrayListSelectionArgs.size()];
                selectionArgs = arrayListSelectionArgs.toArray(selectionArgs);
                String limit = mContext.getString(R.string.result_limit);
                Cursor cursor = mDatabase.query(
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
                    e.onError(new NullPointerException("cursor == null"));
                }
                e.onNext(listKanjis);
                e.onComplete();
            }
        });
    }

    public Observable<Grammar> searchGrammar(final String input) {
        return Observable.create(new ObservableOnSubscribe<Grammar>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Grammar> e) throws Exception {
                mDatabase = getReadableDatabase();
                String selection = DatabaseContract.GrammarContract.Main.COLUMN_ORIGIN + " LIKE ?";
                String[] selectionArgs = new String[]{"%" + input + "%"};
                String limit = mContext.getString(R.string.result_limit);
                Cursor cursor = mDatabase.query(
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
                        Grammar grammar = new Grammar();
                        String origin = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.GrammarContract.Main.COLUMN_ORIGIN));
                        String definition = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.GrammarContract.Main.COLUMN_DEFINITION));
                        grammar.setOrigin(origin);
                        grammar.setDefinition(StringHandling.format(definition));
                        e.onNext(grammar);
                    }
                    cursor.close();
                } else {
                    e.onError(new NullPointerException(""));
                }
                e.onComplete();
            }
        });
    }

    public Observable<List<String>> searchExamplesOfWord(final int id) {
        // SELECT * FROM
        // (SELECT * FROM jpn_vie_main WHERE docid = '1') AS main JOIN
        // jpn_vie_relate_ex JOIN jpn_vie_examples
        // WHERE main.docid = jpn_vie_relate_ex.word_id
        // AND jpn_vie_relate_ex.ex_id = jpn_vie_examples._id
        // ORDER BY c3priority DESC
        return Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<String>> e) throws Exception {
                mDatabase = getReadableDatabase();
                List<String> examples = new ArrayList<>();
                String[] selectionArgs = new String[]{String.valueOf(id)};
                Cursor cursor = mDatabase.rawQuery(
                    "SELECT * FROM (SELECT * FROM jpn_vie_main WHERE docid = ? ) AS main JOIN " +
                        "jpn_vie_relate_ex JOIN " +
                        "jpn_vie_examples WHERE " +
                        "main.docid = jpn_vie_relate_ex.word_id AND " +
                        "jpn_vie_relate_ex.ex_id = jpn_vie_examples._id " +
                        "ORDER BY c3priority DESC", selectionArgs);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        JpnWord jpnWord = new JpnWord();
                        String example = cursor.getString(cursor.getColumnIndex(
                            DatabaseContract.JpnVieContract.Examples.COLUMN_EXAMPLE));
                        examples.add(example);
                    }
                    cursor.close();
                } else {
                    e.onError(new NullPointerException(""));
                }
                e.onNext(examples);
                e.onComplete();
            }
        });
    }

    public void closeDatabase() {
        if (mDatabase != null && mDatabase.isOpen()) {
            mDatabase.close();
        }
    }
}
