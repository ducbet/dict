package com.tmd.dictionary.data.model;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tmd on 18/08/2017.
 */
public class Kanji extends RealmObject implements Serializable {
    @PrimaryKey
    private String origin = "";
    private String hanViet = "";
    private String radical = "";
    private int stroke;
    private String onyomi = "";
    private int level;
    private String kunyomi = "";
    private String meaning = "";
    private RealmList<Component> components;
    private boolean isLearned;
    private int searchedCount;
    private boolean isModified;
    private RealmList<KanjiBox> inBox;

    public boolean isLearned() {
        return isLearned;
    }

    public void setLearned(boolean learned) {
        isLearned = learned;
    }

    public int getSearchedCount() {
        return searchedCount;
    }

    public void setSearchedCount(int searchedCount) {
        this.searchedCount = searchedCount;
    }

    public boolean isModified() {
        return isModified;
    }

    public void setModified(boolean modified) {
        isModified = modified;
    }

    public RealmList<KanjiBox> getInBox() {
        return inBox;
    }

    public void setInBox(RealmList<KanjiBox> inBox) {
        this.inBox = inBox;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getHanViet() {
        return hanViet;
    }

    public void setHanViet(String hanViet) {
        this.hanViet = hanViet;
    }

    public String getRadical() {
        return radical;
    }

    public void setRadical(String radical) {
        this.radical = radical;
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    public String getOnyomi() {
        return onyomi;
    }

    public void setOnyomi(String onyomi) {
        this.onyomi = onyomi;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getKunyomi() {
        return kunyomi;
    }

    public void setKunyomi(String kunyomi) {
        this.kunyomi = kunyomi;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public RealmList<Component> getComponents() {
        return components;
    }

    public void setComponents(RealmList<Component> components) {
        this.components = components;
    }
}
