package com.tmd.dictionary.data.source;

import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.VieWord;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by tmd on 16/08/2017.
 */
public interface DataSource {
    Observable<JpnWord> searchJpnVie(String input);
    Observable<VieWord> searchVieJpn(String input);
    Observable<List<Kanji>> searchKanji(String input);
    Observable<Grammar> searchGrammar(String input);
    Observable<List<String>> searchExamplesOfWord(int id);
    void closeDatabase();
}
