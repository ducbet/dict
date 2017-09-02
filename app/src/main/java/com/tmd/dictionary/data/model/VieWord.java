package com.tmd.dictionary.data.model;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tmd on 26/08/2017.
 */
public class VieWord extends RealmObject implements Serializable {
    @PrimaryKey
    private String origin = "";
    private String kana = "";
    private String definition = "";
    private RealmList<RealmString> examples;
    private boolean isLearned;
    private int searchedCount;
    private boolean isModified;
    private RealmList<VieBox> inBox;

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

    public RealmList<VieBox> getInBox() {
        return inBox;
    }

    public void setInBox(RealmList<VieBox> inBox) {
        this.inBox = inBox;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getKana() {
        return kana;
    }

    public void setKana(String kana) {
        this.kana = kana;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public RealmList<RealmString> getExamples() {
        return examples;
    }

    public void setExamples(RealmList<RealmString> examples) {
        this.examples = examples;
    }
}
