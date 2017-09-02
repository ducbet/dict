package com.tmd.dictionary.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * Created by tmd on 18/08/2017.
 */
@RealmClass
public class Component implements RealmModel, Parcelable {
    private String radical = "";
    private int strokeCount;
    private String hanViet = "";
    private String reading = "";

    public Component() {
    }

    protected Component(Parcel in) {
        radical = in.readString();
        strokeCount = in.readInt();
        hanViet = in.readString();
        reading = in.readString();
    }

    public static final Creator<Component> CREATOR = new Creator<Component>() {
        @Override
        public Component createFromParcel(Parcel in) {
            return new Component(in);
        }

        @Override
        public Component[] newArray(int size) {
            return new Component[size];
        }
    };

    public String getRadical() {
        return radical;
    }

    public void setRadical(String radical) {
        this.radical = radical;
    }

    public int getStrokeCount() {
        return strokeCount;
    }

    public void setStrokeCount(int strokeCount) {
        this.strokeCount = strokeCount;
    }

    public String getHanViet() {
        return hanViet;
    }

    public void setHanViet(String hanViet) {
        this.hanViet = hanViet;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(radical);
        parcel.writeInt(strokeCount);
        parcel.writeString(hanViet);
        parcel.writeString(reading);
    }
}
