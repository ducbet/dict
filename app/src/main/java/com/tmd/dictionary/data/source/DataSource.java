package com.tmd.dictionary.data.source;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.Word;

import java.util.List;

/**
 * Created by tmd on 16/08/2017.
 */
public interface DataSource {
    List<Word> searchJpnVieDefinition(String input);
    List<Kanji> searchKanji(String input);
}
