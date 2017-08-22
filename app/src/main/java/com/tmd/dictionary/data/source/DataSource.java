package com.tmd.dictionary.data.source;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.Word;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by tmd on 16/08/2017.
 */
public interface DataSource {
    Observable<Word> searchJpnVie(String input);
    Observable<Word> searchVieJpn(String input);
    Observable<List<Kanji>> searchKanji(String input);
    Observable<Word> searchGrammar(String input);
    Observable<List<String>> searchExamplesOfWord(int id);
    void closeDatabase();
}
