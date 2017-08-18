package com.tmd.dictionary.data.model;

import java.util.List;

/**
 * Created by tmd on 18/08/2017.
 */
public class Kanji {
    private String mKanji;
    private String mHanViet;
    private String mRadical;
    private int mStroke;
    private String mOnyomi;
    private int mLevel;
    private String mKunyomi;
    private String mMeaning;
    private List<Component> mComponents;

    public String getKanji() {
        return mKanji;
    }

    public void setKanji(String kanji) {
        mKanji = kanji;
    }

    public String getHanViet() {
        return mHanViet;
    }

    public void setHanViet(String hanViet) {
        mHanViet = hanViet;
    }

    public String getRadical() {
        return mRadical;
    }

    public void setRadical(String radical) {
        mRadical = radical;
    }

    public int getStroke() {
        return mStroke;
    }

    public void setStroke(int stroke) {
        mStroke = stroke;
    }

    public String getOnyomi() {
        return mOnyomi;
    }

    public void setOnyomi(String onyomi) {
        mOnyomi = onyomi;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        mLevel = level;
    }

    public String getKunyomi() {
        return mKunyomi;
    }

    public void setKunyomi(String kunyomi) {
        mKunyomi = kunyomi;
    }

    public String getMeaning() {
        return mMeaning;
    }

    public void setMeaning(String meaning) {
        mMeaning = meaning;
    }

    public List<Component> getComponents() {
        return mComponents;
    }

    public void setComponents(List<Component> components) {
        mComponents = components;
    }
}
