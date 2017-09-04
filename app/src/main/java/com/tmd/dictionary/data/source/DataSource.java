package com.tmd.dictionary.data.source;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.History;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.VieWord;

import io.reactivex.Observable;
import io.realm.RealmResults;

/**
 * Created by tmd on 16/08/2017.
 */
public interface DataSource {
    RealmResults<JpnWord> searchJpnWordHasKanjis(String input);
    RealmResults<JpnWord> searchJpnWordNotHasKanjis(String input);
    RealmResults<VieWord> searchVieJpn(String input);
    Observable<RealmResults<Kanji>> searchKanji(String input);
    Observable<RealmResults<Grammar>> searchGrammar(String input);
    RealmResults<JpnWord> chaningJpnQuery(String input, RealmResults<JpnWord> parentsResult);
    RealmResults<VieWord> chaningVieQuery(String input, RealmResults<VieWord> parentsResult);
    void saveToHistory(int type, String key);
    Observable<History> getHistory();
    Observable<Boolean> changeLikeState(int type, String key);
    Observable<Boolean> isLiked(String key);
}
