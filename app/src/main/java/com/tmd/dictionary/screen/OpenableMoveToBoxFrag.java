package com.tmd.dictionary.screen;

import android.content.Context;

import io.realm.RealmResults;

/**
 * Created by tmd on 19/09/2017.
 */
public interface OpenableMoveToBoxFrag {
    Context getContext();
    String getWordOrigin();
    RealmResults getAllFlashcardBoxes();
    void createBox(String name, String description);
}
