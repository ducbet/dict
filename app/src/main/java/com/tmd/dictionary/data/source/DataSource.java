package com.tmd.dictionary.data.source;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.VieWord;

import java.util.List;

import io.reactivex.Observable;
import io.realm.RealmResults;

/**
 * Created by tmd on 16/08/2017.
 */
public interface DataSource {
    Observable<RealmResults<JpnWord>> searchJpnVie(String input);
    Observable<RealmResults<VieWord>> searchVieJpn(String input);
    Observable<RealmResults<Kanji>> searchKanji(String input);
    Observable<RealmResults<Grammar>> searchGrammar(String input);
    Observable<List<String>> searchExamplesOfWord(int id);
}
