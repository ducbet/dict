package com.tmd.dictionary.data.source.local;

import android.content.Context;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.data.source.DataSource;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by tmd on 16/08/2017.
 */
public class LocalDataSource implements DataSource {
    private _CRUDHelper mCRUDHelper;
    private Context mContext;

    public LocalDataSource(Context context) {
        mContext = context;
        mCRUDHelper = new _CRUDHelper(context);
    }

    @Override
    public Observable<JpnWord> searchJpnVie(String input) {
        return mCRUDHelper.searchJpnVie(input);
    }

    @Override
    public Observable<VieWord> searchVieJpn(String input) {
        return mCRUDHelper.searchVieJpn(input);
    }

    @Override
    public Observable<List<Kanji>> searchKanji(String input) {
        return mCRUDHelper.searchKanji(input);
    }

    @Override
    public Observable<Grammar> searchGrammar(String input) {
        return mCRUDHelper.searchGrammar(input);
    }

    @Override
    public Observable<List<String>> searchExamplesOfWord(int id) {
        return mCRUDHelper.searchExamplesOfWord(id);
    }

    @Override
    public void closeDatabase() {
        mCRUDHelper.closeDatabase();
    }
}
