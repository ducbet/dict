package com.tmd.dictionary.data.model;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by tmd on 18/08/2017.
 */
public class Component extends RealmObject implements Serializable{
    private String radical = "";
    private int strokeCount;
    private String hanViet = "";
    private String reading = "";

    public String getRadical() {
        return radical;
    }

    public void setRadical(String radical) {
        this.radical = radical;
    }

    public int getStrokeCount() {
        return strokeCount;
    }

    public void setStrokeCount(int strokeCount) {
        this.strokeCount = strokeCount;
    }

    public String getHanViet() {
        return hanViet;
    }

    public void setHanViet(String hanViet) {
        this.hanViet = hanViet;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }
}
