package com.tmd.dictionary.data.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by tmd on 02/09/2017.
 */
public class History extends RealmObject {
    private RealmList<RealmString> type;
    private RealmList<RealmString> primaryKey;

    public RealmList<RealmString> getType() {
        return type;
    }

    public void setType(RealmList<RealmString> type) {
        this.type = type;
    }

    public RealmList<RealmString> getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(RealmList<RealmString> primaryKey) {
        this.primaryKey = primaryKey;
    }
}
