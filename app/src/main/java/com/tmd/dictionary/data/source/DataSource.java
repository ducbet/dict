package com.tmd.dictionary.data.source;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.GrammarBox;
import com.tmd.dictionary.data.model.History;
import com.tmd.dictionary.data.model.JpnBox;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.KanjiBox;
import com.tmd.dictionary.data.model.LikedWord;
import com.tmd.dictionary.data.model.VieBox;
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
    void createHistoryObjectIfNotExist();
    void saveToHistory(JpnWord jpnWord);
    void saveToHistory(VieWord vieWord);
    void saveToHistory(Kanji kanji);
    void saveToHistory(Grammar grammar);
    History getHistory();
    LikedWord getLikedWords();
    void createLikedWordObjectIfNotExist();
    Observable<Boolean> changeLikeState(JpnWord jpnWord);
    Observable<Boolean> changeLikeState(VieWord vieWord);
    Observable<Boolean> changeLikeState(Kanji kanji);
    Observable<Boolean> changeLikeState(Grammar grammar);
    Observable<Boolean> isLiked(JpnWord jpnWord);
    Observable<Boolean> isLiked(VieWord vieWord);
    Observable<Boolean> isLiked(Kanji kanji);
    Observable<Boolean> isLiked(Grammar grammar);
    void createFlashcardBox(JpnBox newBox);
    void createFlashcardBox(VieBox newBox);
    void createFlashcardBox(KanjiBox newBox);
    void createFlashcardBox(GrammarBox newBox);
    RealmResults<JpnBox> getAllJpnBoxes();
    RealmResults<VieBox> getAllVieBoxes();
    RealmResults<KanjiBox> getAllKanjiBoxes();
    RealmResults<GrammarBox> getAllGrammarBoxes();
}
