package com.tmd.dictionary.data.model;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by tmd on 25/08/2017.
 */
public class RealmString extends RealmObject implements Serializable {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
