package com.tmd.dictionary.data.source;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.History;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.LikedWord;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.data.source.local.LocalDataSource;

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
    public RealmResults<JpnWord> searchEqualJpnWordHasKanjis(String input) {
        return mLocalDataSource.searchEqualJpnWordHasKanjis(input);
    }

    @Override
    public RealmResults<JpnWord> searchLikeJpnWordHasKanjis(String input) {
        return mLocalDataSource.searchLikeJpnWordHasKanjis(input);
    }

    @Override
    public RealmResults<JpnWord> searchEqualJpnWordNotHasKanjis(String input) {
        return mLocalDataSource.searchEqualJpnWordNotHasKanjis(input);
    }

    @Override
    public RealmResults<JpnWord> searchLikeJpnWordNotHasKanjis(String input) {
        return mLocalDataSource.searchLikeJpnWordNotHasKanjis(input);
    }

    @Override
    public RealmResults<VieWord> searchVieJpn(String input) {
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

    public RealmResults<JpnWord> chaningJpnQueryEqual(String input,
                                                      RealmResults<JpnWord> parentsResult) {
        return mLocalDataSource.chaningJpnQueryEqual(input, parentsResult);
    }

    @Override
    public RealmResults<JpnWord> chaningJpnQueryLike(String input,
                                                     RealmResults<JpnWord> parentsResult) {
        return mLocalDataSource.chaningJpnQueryLike(input, parentsResult);
    }

    @Override
    public RealmResults<VieWord> chaningVieQuery(String input,
                                                 RealmResults<VieWord> parentsResult) {
        return mLocalDataSource.chaningVieQuery(input, parentsResult);
    }

    @Override
    public void createHistoryObjectIfNotExist() {
        mLocalDataSource.createHistoryObjectIfNotExist();
    }

    @Override
    public void saveToHistory(JpnWord jpnWord) {
        mLocalDataSource.saveToHistory(jpnWord);
    }

    @Override
    public void saveToHistory(VieWord vieWord) {
        mLocalDataSource.saveToHistory(vieWord);
    }

    @Override
    public void saveToHistory(Kanji kanji) {
        mLocalDataSource.saveToHistory(kanji);
    }

    @Override
    public void saveToHistory(Grammar grammar) {
        mLocalDataSource.saveToHistory(grammar);
    }

    @Override
    public History getHistory() {
        return mLocalDataSource.getHistory();
    }

    @Override
    public LikedWord getLikedWords() {
        return mLocalDataSource.getLikedWords();
    }

    @Override
    public void createLikedWordObjectIfNotExist() {
        mLocalDataSource.createLikedWordObjectIfNotExist();
    }

    @Override
    public Observable<Boolean> changeLikeState(JpnWord jpnWord) {
        return mLocalDataSource.changeLikeState(jpnWord);
    }

    @Override
    public Observable<Boolean> changeLikeState(VieWord vieWord) {
        return mLocalDataSource.changeLikeState(vieWord);
    }

    @Override
    public Observable<Boolean> changeLikeState(Kanji kanji) {
        return mLocalDataSource.changeLikeState(kanji);
    }

    @Override
    public Observable<Boolean> changeLikeState(Grammar grammar) {
        return mLocalDataSource.changeLikeState(grammar);
    }

    @Override
    public Observable<Boolean> isLiked(JpnWord jpnWord) {
        return mLocalDataSource.isLiked(jpnWord);
    }

    @Override
    public Observable<Boolean> isLiked(VieWord vieWord) {
        return mLocalDataSource.isLiked(vieWord);
    }

    @Override
    public Observable<Boolean> isLiked(Kanji kanji) {
        return mLocalDataSource.isLiked(kanji);
    }

    @Override
    public Observable<Boolean> isLiked(Grammar grammar) {
        return mLocalDataSource.isLiked(grammar);
    }
}
