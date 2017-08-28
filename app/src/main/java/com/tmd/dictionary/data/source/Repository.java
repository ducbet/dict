package com.tmd.dictionary.data.source;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.data.source.local.LocalDataSource;

import java.util.List;

import io.reactivex.Observable;
import io.realm.RealmResults;

/**
 * Created by tmd on 16/08/2017.
 */
public class Repository implements DataSource {
    private LocalDataSource mLocalDataSource;

    public Repository(LocalDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    public Observable<RealmResults<JpnWord>> searchJpnVie(String input) {
        return mLocalDataSource.searchJpnVie(input);
    }

    @Override
    public Observable<RealmResults<VieWord>> searchVieJpn(String input) {
        return mLocalDataSource.searchVieJpn(input);
    }

    @Override
    public Observable<RealmResults<Kanji>> searchKanji(String input) {
        return mLocalDataSource.searchKanji(input);
    }

    @Override
    public Observable<RealmResults<Grammar>> searchGrammar(String input) {
        return mLocalDataSource.searchGrammar(input);
    }

    @Override
    public Observable<List<String>> searchExamplesOfWord(int id) {
        return mLocalDataSource.searchExamplesOfWord(id);
    }
}
