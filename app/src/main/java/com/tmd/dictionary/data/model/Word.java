package com.tmd.dictionary.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tmd on 18/08/2017.
 */
public class Word implements Serializable {
    private int mId;
    private String mOrigin = "";
    private String mKana = "";
    private String mDefinition = "";
    private int mPriority;
    private List<String> mExamples;
    private List<Kanji> mKanjis;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getOrigin() {
        return mOrigin;
    }

    public void setOrigin(String origin) {
        mOrigin = origin;
    }

    public String getKana() {
        return mKana;
    }

    public void setKana(String kana) {
        mKana = kana;
    }

    public String getDefinition() {
        return mDefinition;
    }

    public void setDefinition(String definition) {
        mDefinition = definition;
    }

    public int getPriority() {
        return mPriority;
    }

    public void setPriority(int priority) {
        mPriority = priority;
    }

    public List<String> getExamples() {
        return mExamples;
    }

    public void setExamples(List<String> examples) {
        mExamples = examples;
    }

    public List<Kanji> getKanjis() {
        return mKanjis;
    }

    public void setKanjis(List<Kanji> kanjis) {
        mKanjis = kanjis;
    }
}
