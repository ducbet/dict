package com.tmd.dictionary.data.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by tmd on 02/09/2017.
 */
public class LikedWord extends RealmObject {
    private RealmList<RealmInteger> type;
    private RealmList<RealmString> jsonWord;

    public RealmList<RealmInteger> getType() {
        return type;
    }

    public void setType(RealmList<RealmInteger> type) {
        this.type = type;
    }

    public RealmList<RealmString> getJsonWord() {
        return jsonWord;
    }

    public void setJsonWord(RealmList<RealmString> primaryKey) {
        this.jsonWord = primaryKey;
    }
}
