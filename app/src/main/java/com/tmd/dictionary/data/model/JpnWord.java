package com.tmd.dictionary.data.model;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tmd on 18/08/2017.
 */
public class JpnWord extends RealmObject implements Serializable {
    @PrimaryKey
    private String origin = "";
    private String kana = "";
    private String definition = "";
    private int priority;
    private RealmList<RealmString> examples;
    private RealmList<Kanji> kanjis;

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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public RealmList<RealmString> getExamples() {
        return examples;
    }

    public void setExamples(RealmList<RealmString> examples) {
        this.examples = examples;
    }

    public RealmList<Kanji> getKanjis() {
        return kanjis;
    }

    public void setKanjis(List<Kanji> kanjis) {
        RealmList<Kanji> realmList = new RealmList<>();
        for (Kanji kanji : kanjis) {
            realmList.add(kanji);
        }
        this.kanjis = realmList;
    }
}
