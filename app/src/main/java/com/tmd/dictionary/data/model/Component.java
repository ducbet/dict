package com.tmd.dictionary.data.model;

/**
 * Created by tmd on 18/08/2017.
 */
public class Component {
    private String mRadical = "";
    private String mStroke = "";
    private String mHanViet = "";
    private String mMeaning = "";

    public String getRadical() {
        return mRadical;
    }

    public void setRadical(String radical) {
        mRadical = radical;
    }

    public String getStroke() {
        return mStroke;
    }

    public void setStroke(String stroke) {
        mStroke = stroke;
    }

    public String getHanViet() {
        return mHanViet;
    }

    public void setHanViet(String hanViet) {
        mHanViet = hanViet;
    }

    public String getMeaning() {
        return mMeaning;
    }

    public void setMeaning(String meaning) {
        mMeaning = meaning;
    }
}
