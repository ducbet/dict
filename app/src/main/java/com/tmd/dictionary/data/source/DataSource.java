package com.tmd.dictionary.data.source;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.Word;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by tmd on 16/08/2017.
 */
public interface DataSource {
    Observable<Word> searchJpnVieDefinition(String input);
    Observable<List<Kanji>> searchKanjiMeaning(String input);
}
