package com.tmd.dictionary.data.source.local;

import android.content.Context;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.data.source.DataSource;

import java.util.List;

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
    public List<Word> searchJpnVieDefinition(String input) {
        return mCRUDHelper.searchJpnVieDefinition(input);
    }

    @Override
    public List<Kanji> searchKanji(String input) {
        return mCRUDHelper.searchKanji(input);
    }
}
