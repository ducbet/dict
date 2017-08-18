package com.tmd.dictionary.data.source;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.data.source.local.LocalDataSource;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by tmd on 16/08/2017.
 */
public class Repository implements DataSource {
    private LocalDataSource mLocalDataSource;

    public Repository(LocalDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    public Observable<Word> searchJpnVieDefinition(String input) {
        return mLocalDataSource.searchJpnVieDefinition(input);
    }

    @Override
    public List<Kanji> searchKanji(String input) {
        return mLocalDataSource.searchKanji(input);
    }
}
