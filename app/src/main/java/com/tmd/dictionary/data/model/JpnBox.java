package com.tmd.dictionary.data.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by tmd on 02/09/2017.
 */
public class JpnBox extends RealmObject {
    private RealmList<JpnWord> words;
    private RealmString name;
    private RealmString description;
    private RealmString craeteAt;
    private RealmString updateAt;

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
