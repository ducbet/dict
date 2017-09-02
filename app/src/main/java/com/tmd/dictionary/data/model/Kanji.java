package com.tmd.dictionary.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by tmd on 18/08/2017.
 */
@RealmClass
public class Kanji implements RealmModel, Parcelable {
    @PrimaryKey
    private String origin = "";
    private String hanViet = "";
    private String radical = "";
    private int stroke;
    private String onyomi = "";
    private int level;
    private String kunyomi = "";
    private String meaning = "";
    private RealmList<Component> components;
    private boolean isLearned;
    private int searchedCount;
    private boolean isModified;
    private RealmList<KanjiBox> inBox;

    public Kanji() {
    }

    protected Kanji(Parcel in) {
        origin = in.readString();
        hanViet = in.readString();
        radical = in.readString();
        stroke = in.readInt();
        onyomi = in.readString();
        level = in.readInt();
        kunyomi = in.readString();
        meaning = in.readString();
        isLearned = in.readByte() != 0;
        searchedCount = in.readInt();
        isModified = in.readByte() != 0;
    }

    public static final Creator<Kanji> CREATOR = new Creator<Kanji>() {
        @Override
        public Kanji createFromParcel(Parcel in) {
            return new Kanji(in);
        }

        @Override
        public Kanji[] newArray(int size) {
            return new Kanji[size];
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

    public RealmList<KanjiBox> getInBox() {
        return inBox;
    }

    public void setInBox(RealmList<KanjiBox> inBox) {
        this.inBox = inBox;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getHanViet() {
        return hanViet;
    }

    public void setHanViet(String hanViet) {
        this.hanViet = hanViet;
    }

    public String getRadical() {
        return radical;
    }

    public void setRadical(String radical) {
        this.radical = radical;
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    public String getOnyomi() {
        return onyomi;
    }

    public void setOnyomi(String onyomi) {
        this.onyomi = onyomi;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getKunyomi() {
        return kunyomi;
    }

    public void setKunyomi(String kunyomi) {
        this.kunyomi = kunyomi;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public RealmList<Component> getComponents() {
        return components;
    }

    public void setComponents(RealmList<Component> components) {
        this.components = components;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(origin);
        parcel.writeString(hanViet);
        parcel.writeString(radical);
        parcel.writeInt(stroke);
        parcel.writeString(onyomi);
        parcel.writeInt(level);
        parcel.writeString(kunyomi);
        parcel.writeString(meaning);
        parcel.writeByte((byte) (isLearned ? 1 : 0));
        parcel.writeInt(searchedCount);
        parcel.writeByte((byte) (isModified ? 1 : 0));
    }
}
