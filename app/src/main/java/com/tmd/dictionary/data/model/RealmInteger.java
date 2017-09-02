package com.tmd.dictionary.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * Created by tmd on 02/09/2017.
 */
@RealmClass
public class RealmInteger implements RealmModel, Parcelable {
    private int value;

    public RealmInteger() {
    }

    public RealmInteger(int value) {
        this.value = value;
    }

    protected RealmInteger(Parcel in) {
        value = in.readInt();
    }

    public static final Creator<RealmInteger> CREATOR = new Creator<RealmInteger>() {
        @Override
        public RealmInteger createFromParcel(Parcel in) {
            return new RealmInteger(in);
        }

        @Override
        public RealmInteger[] newArray(int size) {
            return new RealmInteger[size];
        }
    };

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(value);
    }
}
