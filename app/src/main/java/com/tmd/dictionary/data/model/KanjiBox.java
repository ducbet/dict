package com.tmd.dictionary.data.model;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

import static com.tmd.dictionary.staticfinal.ConstantValue.SIMPLE_DATE_FORMAT;

/**
 * Created by tmd on 02/09/2017.
 */
public class KanjiBox extends RealmObject {
    private RealmList<Kanji> kanjis;
    private RealmString name;
    private RealmString description;
    private RealmString craeteAt;
    private RealmString updateAt;

    public KanjiBox() {
    }

    public KanjiBox(String name, String description) {
        this.kanjis = new RealmList<>();
        this.name = new RealmString(name);
        this.description = new RealmString(description);
        this.craeteAt = new RealmString(SIMPLE_DATE_FORMAT.format(new Date()));
        this.updateAt = new RealmString(SIMPLE_DATE_FORMAT.format(new Date()));
    }

    public RealmList<Kanji> getKanjis() {
        return kanjis;
    }

    public void setKanjis(RealmList<Kanji> kanjis) {
        this.kanjis = kanjis;
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
