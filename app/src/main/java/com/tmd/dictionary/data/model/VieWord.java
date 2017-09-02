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
public class VieWord implements RealmModel, Parcelable {
    @PrimaryKey
    private String origin = "";
    private String kana = "";
    private String definition = "";
    private RealmList<RealmString> examples;
    private boolean isLearned;
    private int searchedCount;
    private boolean isModified;
    private RealmList<VieBox> inBox;

    public VieWord() {
    }

    protected VieWord(Parcel in) {
        origin = in.readString();
        kana = in.readString();
        definition = in.readString();
        isLearned = in.readByte() != 0;
        searchedCount = in.readInt();
        isModified = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(origin);
        dest.writeString(kana);
        dest.writeString(definition);
        dest.writeByte((byte) (isLearned ? 1 : 0));
        dest.writeInt(searchedCount);
        dest.writeByte((byte) (isModified ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VieWord> CREATOR = new Creator<VieWord>() {
        @Override
        public VieWord createFromParcel(Parcel in) {
            return new VieWord(in);
        }

        @Override
        public VieWord[] newArray(int size) {
            return new VieWord[size];
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

    public RealmList<VieBox> getInBox() {
        return inBox;
    }

    public void setInBox(RealmList<VieBox> inBox) {
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
}
