package com.tmd.dictionary.data.source;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.History;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.data.source.local.LocalDataSource;

import io.reactivex.Observable;
import io.realm.Realm;
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
    public RealmResults<JpnWord> searchJpnWordHasKanjis(String input) {
        return mLocalDataSource.searchJpnWordHasKanjis(input);
    }

    @Override
    public RealmResults<JpnWord> searchJpnWordNotHasKanjis(String input) {
        return mLocalDataSource.searchJpnWordNotHasKanjis(input);
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
    public RealmResults<JpnWord> chaningQuery(String input, RealmResults<JpnWord> parentsResult) {
        return mLocalDataSource.chaningQuery(input, parentsResult);
    }

    @Override
    public void saveToHistory(Realm realm, int type, String key) {
        mLocalDataSource.saveToHistory(realm, type, key);
    }

    @Override
    public Observable<History> getHistory() {
        return mLocalDataSource.getHistory();
    }

    @Override
    public Observable<Boolean> changeLikeState(int type, String key) {
        return mLocalDataSource.changeLikeState(type, key);
    }

    @Override
    public Observable<Boolean> isLiked(String key) {
        return mLocalDataSource.isLiked(key);
    }
}
