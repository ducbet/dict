package com.tmd.dictionary.data.source.local;

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
import com.tmd.dictionary.data.source.DataSource;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by tmd on 16/08/2017.
 */
public class LocalDataSource implements DataSource {
    private _CRUDHelper mCRUDHelper;

    public LocalDataSource(Realm realm) {
        mCRUDHelper = new _CRUDHelper(realm);
    }

    @Override
    public RealmResults<JpnWord> searchJpnWordHasKanjis(String input) {
        return mCRUDHelper.searchJpnWordHasKanjis(input);
    }

    @Override
    public RealmResults<JpnWord> searchJpnWordNotHasKanjis(String input) {
        return mCRUDHelper.searchJpnWordNotHasKanjis(input);
    }

    @Override
    public RealmResults<VieWord> searchVieJpn(String input) {
        return mCRUDHelper.searchVieJpn(input);
    }

    @Override
    public Observable<RealmResults<Kanji>> searchKanji(String input) {
        return mCRUDHelper.searchKanji(input);
    }

    @Override
    public Observable<RealmResults<Grammar>> searchGrammar(String input) {
        return mCRUDHelper.searchGrammar(input);
    }

    public RealmResults<JpnWord> chaningJpnQuery(String input,
                                                 RealmResults<JpnWord> parentsResult) {
        return mCRUDHelper.chaningJpnQuery(input, parentsResult);
    }

    @Override
    public RealmResults<VieWord> chaningVieQuery(String input,
                                                 RealmResults<VieWord> parentsResult) {
        return mCRUDHelper.chaningVieQuery(input, parentsResult);
    }

    @Override
    public void createHistoryObjectIfNotExist() {
        mCRUDHelper.createHistoryObjectIfNotExist();
    }

    @Override
    public void saveToHistory(JpnWord jpnWord) {
        mCRUDHelper.saveToHistory(jpnWord);
    }

    @Override
    public void saveToHistory(VieWord vieWord) {
        mCRUDHelper.saveToHistory(vieWord);
    }

    @Override
    public void saveToHistory(Kanji kanji) {
        mCRUDHelper.saveToHistory(kanji);
    }

    @Override
    public void saveToHistory(Grammar grammar) {
        mCRUDHelper.saveToHistory(grammar);
    }

    @Override
    public History getHistory() {
        return mCRUDHelper.getHistory();
    }

    @Override
    public LikedWord getLikedWords() {
        return mCRUDHelper.getLikedWords();
    }

    @Override
    public void createLikedWordObjectIfNotExist() {
        mCRUDHelper.createLikedWordObjectIfNotExist();
    }

    @Override
    public Observable<Boolean> changeLikeState(JpnWord jpnWord) {
        return mCRUDHelper.changeLikeState(jpnWord);
    }

    @Override
    public Observable<Boolean> changeLikeState(VieWord vieWord) {
        return mCRUDHelper.changeLikeState(vieWord);
    }

    @Override
    public Observable<Boolean> changeLikeState(Kanji kanji) {
        return mCRUDHelper.changeLikeState(kanji);
    }

    @Override
    public Observable<Boolean> changeLikeState(Grammar grammar) {
        return mCRUDHelper.changeLikeState(grammar);
    }

    @Override
    public Observable<Boolean> isLiked(JpnWord jpnWord) {
        return mCRUDHelper.isLiked(jpnWord);
    }

    @Override
    public Observable<Boolean> isLiked(VieWord vieWord) {
        return mCRUDHelper.isLiked(vieWord);
    }

    @Override
    public Observable<Boolean> isLiked(Kanji kanji) {
        return mCRUDHelper.isLiked(kanji);
    }

    @Override
    public Observable<Boolean> isLiked(Grammar grammar) {
        return mCRUDHelper.isLiked(grammar);
    }

    @Override
    public JpnBox createFlashcardBox(JpnBox newBox) {
        return mCRUDHelper.createFlashcardBox(newBox);
    }

    @Override
    public VieBox createFlashcardBox(VieBox newBox) {
        return mCRUDHelper.createFlashcardBox(newBox);
    }

    @Override
    public KanjiBox createFlashcardBox(KanjiBox newBox) {
        return mCRUDHelper.createFlashcardBox(newBox);
    }

    @Override
    public GrammarBox createFlashcardBox(GrammarBox newBox) {
        return mCRUDHelper.createFlashcardBox(newBox);
    }
}
