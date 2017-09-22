package com.tmd.dictionary.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

import static com.tmd.dictionary.staticfinal.ConstantValue.SIMPLE_DATE_FORMAT;

/**
 * Created by tmd on 02/09/2017.
 */
public class JpnBox extends RealmObject implements Parcelable {
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

    /**
     * Parcelable
     */
    protected JpnBox(Parcel in) {
        name = in.readParcelable(RealmString.class.getClassLoader());
        description = in.readParcelable(RealmString.class.getClassLoader());
        craeteAt = in.readParcelable(RealmString.class.getClassLoader());
        updateAt = in.readParcelable(RealmString.class.getClassLoader());
    }

    public static final Creator<JpnBox> CREATOR = new Creator<JpnBox>() {
        @Override
        public JpnBox createFromParcel(Parcel in) {
            return new JpnBox(in);
        }

        @Override
        public JpnBox[] newArray(int size) {
            return new JpnBox[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(name, i);
        parcel.writeParcelable(description, i);
        parcel.writeParcelable(craeteAt, i);
        parcel.writeParcelable(updateAt, i);
    }
}
