package com.tmd.dictionary.data.source;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.VieWord;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by tmd on 16/08/2017.
 */
public interface DataSource {
    Observable<RealmResults<JpnWord>> searchJpnVie(String input);
    Observable<RealmResults<VieWord>> searchVieJpn(String input);
    Observable<RealmResults<Kanji>> searchKanji(String input);
    Observable<RealmResults<Grammar>> searchGrammar(String input);
    void saveToHistory(Realm realm, final int type, final String primaryKey);
}
