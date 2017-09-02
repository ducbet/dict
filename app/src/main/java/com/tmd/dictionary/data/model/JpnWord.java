package com.tmd.dictionary.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by tmd on 18/08/2017.
 */
@RealmClass
public class JpnWord implements RealmModel, Parcelable {
    @PrimaryKey
    private String origin = "";
    private String kana = "";
    private String definition = "";
    private int priority;
    private RealmList<RealmString> examples;
    private RealmList<Kanji> kanjis;
    private boolean isLearned;
    private int searchedCount;
    private boolean isModified;
    private RealmList<JpnBox> inBox;

    public JpnWord() {
    }

    protected JpnWord(Parcel in) {
        origin = in.readString();
        kana = in.readString();
        definition = in.readString();
        priority = in.readInt();
        isLearned = in.readByte() != 0;
        searchedCount = in.readInt();
        isModified = in.readByte() != 0;
    }

    public static final Creator<JpnWord> CREATOR = new Creator<JpnWord>() {
        @Override
        public JpnWord createFromParcel(Parcel in) {
            return new JpnWord(in);
        }

        @Override
        public JpnWord[] newArray(int size) {
            return new JpnWord[size];
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

    public RealmList<JpnBox> getInBox() {
        return inBox;
    }

    public void setInBox(RealmList<JpnBox> inBox) {
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public RealmList<RealmString> getExamples() {
        return examples;
    }

    public void setExamples(RealmList<RealmString> examples) {
        this.examples = examples;
    }

    public RealmList<Kanji> getKanjis() {
        return kanjis;
    }

    public void setKanjis(List<Kanji> kanjis) {
        RealmList<Kanji> realmList = new RealmList<>();
        for (Kanji kanji : kanjis) {
            realmList.add(kanji);
        }
        this.kanjis = realmList;
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
        parcel.writeInt(priority);
        parcel.writeByte((byte) (isLearned ? 1 : 0));
        parcel.writeInt(searchedCount);
        parcel.writeByte((byte) (isModified ? 1 : 0));
    }
}
