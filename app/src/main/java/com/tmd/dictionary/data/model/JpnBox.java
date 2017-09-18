package com.tmd.dictionary.data.model;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

import static com.tmd.dictionary.staticfinal.ConstantValue.SIMPLE_DATE_FORMAT;

/**
 * Created by tmd on 02/09/2017.
 */
public class JpnBox extends RealmObject {
    private RealmList<JpnWord> words;
    private RealmString name;
    private RealmString description;
    private RealmString craeteAt;
    private RealmString updateAt;

    public JpnBox() {
    }

    public JpnBox(String name, String description) {
        this.words = new RealmList<>();
        this.name = new RealmString(name);
        this.description = new RealmString(description);
        this.craeteAt = new RealmString(SIMPLE_DATE_FORMAT.format(new Date()));
        this.updateAt = new RealmString(SIMPLE_DATE_FORMAT.format(new Date()));
    }

    public RealmList<JpnWord> getWords() {
        return words;
    }

    public void setWords(RealmList<JpnWord> words) {
        this.words = words;
    }

    public RealmString getName() {
        return name;
    }

    public void setName(RealmString name) {
        this.name = name;
    }

    public RealmString getDescription() {
        return description;
    }

    public void setDescription(RealmString description) {
        this.description = description;
    }

    public RealmString getCraeteAt() {
        return craeteAt;
    }

    public void setCraeteAt(RealmString craeteAt) {
        this.craeteAt = craeteAt;
    }

    public RealmString getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(RealmString updateAt) {
        this.updateAt = updateAt;
    }
}
