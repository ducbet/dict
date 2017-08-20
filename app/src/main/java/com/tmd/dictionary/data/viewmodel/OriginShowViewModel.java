package com.tmd.dictionary.data.viewmodel;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.Word;

/**
 * Created by tmd on 20/08/2017.
 */
public class OriginShowViewModel {
    private Word mWord;

    public Word getWord() {
        return mWord;
    }

    public void setWord(Word word) {
        mWord = word;
    }

    public Kanji getKanji() {
        return mKanji;
    }

    public void setKanji(Kanji kanji) {
        mKanji = kanji;
    }

    private Kanji mKanji;
}
