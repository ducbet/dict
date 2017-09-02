package com.tmd.dictionary.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by tmd on 26/08/2017.
 */
@RealmClass
public class Grammar implements RealmModel, Parcelable {
    @PrimaryKey
    private String origin = "";
    private String kana = "";
    private String definition = "";
    private RealmList<RealmString> examples;
    private boolean isLearned;
    private int searchedCount;
    private boolean isModified;
    private RealmList<GrammarBox> inBox;

    public Grammar() {
    }

    protected Grammar(Parcel in) {
        origin = in.readString();
        kana = in.readString();
        definition = in.readString();
        isLearned = in.readByte() != 0;
        searchedCount = in.readInt();
        isModified = in.readByte() != 0;
    }

    public static final Creator<Grammar> CREATOR = new Creator<Grammar>() {
        @Override
        public Grammar createFromParcel(Parcel in) {
            return new Grammar(in);
        }

        @Override
        public Grammar[] newArray(int size) {
            return new Grammar[size];
        }
    };

    public boolean isLearned() {
        return isLearned;
    }

    public void setLearned(boolean learned) {
        isLearned = learned;
    }

    public int getSearchedCount() {
        return searchedCount;
    }

    public void setSearchedCount(int searchedCount) {
        this.searchedCount = searchedCount;
    }

    public boolean isModified() {
        return isModified;
    }

    public void setModified(boolean modified) {
        isModified = modified;
    }

    public RealmList<GrammarBox> getInBox() {
        return inBox;
    }

    public void setInBox(RealmList<GrammarBox> inBox) {
        this.inBox = inBox;
    }

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

    public RealmList<RealmString> getExamples() {
        return examples;
    }

    public void setExamples(RealmList<RealmString> examples) {
        this.examples = examples;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(origin);
        parcel.writeString(kana);
        parcel.writeString(definition);
        parcel.writeByte((byte) (isLearned ? 1 : 0));
        parcel.writeInt(searchedCount);
        parcel.writeByte((byte) (isModified ? 1 : 0));
    }
}
