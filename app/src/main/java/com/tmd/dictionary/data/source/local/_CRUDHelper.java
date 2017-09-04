package com.tmd.dictionary.data.source.local;

import com.google.gson.Gson;
import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.History;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.RealmInteger;
import com.tmd.dictionary.data.model.RealmString;
import com.tmd.dictionary.data.model.TemporaryBox;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.data.source.DataSource;
import com.tmd.dictionary.staticfinal.CustomGson;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static com.tmd.dictionary.staticfinal.ConstantValue.INT_GRAMMAR;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_JPN_WORD;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_KANJI;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_VIE_WORD;

/**
 * Created by tmd on 09/07/2017.
 */
public class _CRUDHelper implements DataSource {
    private Realm mRealm;

    public _CRUDHelper(Realm realm) {
        mRealm = realm;
    }

    @Override
    public RealmResults<JpnWord> searchJpnWordHasKanjis(final String input) {
        RealmResults<JpnWord> jpnWords = mRealm.where(JpnWord.class)
            .like("origin", "*" + input + "*")
            .findAllAsync();
        return jpnWords;
    }

    @Override
    public RealmResults<JpnWord> searchJpnWordNotHasKanjis(String input) {
        RealmResults<JpnWord> jpnWords = mRealm.where(JpnWord.class)
            .like("kana", "*" + input + "*")
            .findAllAsync();
        return jpnWords;
    }

    @Override
    public RealmResults<VieWord> searchVieJpn(final String input) {
        RealmResults<VieWord> vieWords = mRealm.where(VieWord.class)
            .like("origin", "*" + input + "*")
            .or()
            .like("kana", "*" + input + "*")
            .findAllAsync();
        return vieWords;
    }

    @Override
    public Observable<RealmResults<Kanji>> searchKanji(final String input) {
        // SELECT * FROM kanji_main WHERE kanji = ?
        return Observable.create(new ObservableOnSubscribe<RealmResults<Kanji>>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<RealmResults<Kanji>> e)
                throws Exception {
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmQuery query = realm.where(Kanji.class);
                        for (int i = 0; i < input.length(); i++) {
                            query = query.or().equalTo("kanji", input.charAt(i) + "");
                        }
                        RealmResults<Kanji> realmResults = query.findAll();
                        e.onNext(realmResults);
                        e.onComplete();
                    }
                });
            }
        });
    }

    @Override
    public Observable<RealmResults<Grammar>> searchGrammar(final String input) {
        return Observable.create(new ObservableOnSubscribe<RealmResults<Grammar>>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<RealmResults<Grammar>> e)
                throws Exception {
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<Grammar> grammars = realm.where(Grammar.class)
                            .like("origin", "*" + input + "*")
                            .findAll();
                        e.onNext(grammars);
                        e.onComplete();
                    }
                });
            }
        });
    }

    public RealmResults<JpnWord> chaningJpnQuery(String input,
                                                 RealmResults<JpnWord> parentsResult) {
        RealmResults<JpnWord> jpnWords = parentsResult.where()
            .like("origin", "*" + input + "*")
            .or()
            .like("kana", "*" + input + "*")
            .findAllAsync();
        return jpnWords;
    }

    @Override
    public RealmResults<VieWord> chaningVieQuery(String input,
                                                 RealmResults<VieWord> parentsResult) {
        RealmResults<VieWord> vieWords = parentsResult.where()
            .like("origin", "*" + input + "*")
            .or()
            .like("kana", "*" + input + "*")
            .findAllAsync();
        return vieWords;
    }

    @Override
    public void saveToHistory(final JpnWord jpnWord) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                History history = realm.where(History.class).findFirst();
                if (history == null) {
                    history = realm.createObject(History.class);
                }
                Gson gson = CustomGson.getGson();
                String json = gson.toJson(jpnWord);
                history.getPrimaryKey().add(0, new RealmString(json));
                history.getType().add(0, new RealmInteger(INT_JPN_WORD));
            }
        });
    }

    @Override
    public void saveToHistory(final VieWord vieWord) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                History history = realm.where(History.class).findFirst();
                if (history == null) {
                    history = realm.createObject(History.class);
                }
                Gson gson = CustomGson.getGson();
                String json = gson.toJson(vieWord);
                history.getPrimaryKey().add(0, new RealmString(json));
                history.getType().add(0, new RealmInteger(INT_VIE_WORD));
            }
        });
    }

    @Override
    public void saveToHistory(final Kanji kanji) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                History history = realm.where(History.class).findFirst();
                if (history == null) {
                    history = realm.createObject(History.class);
                }
                Gson gson = CustomGson.getGson();
                String json = gson.toJson(kanji);
                history.getPrimaryKey().add(0, new RealmString(json));
                history.getType().add(0, new RealmInteger(INT_KANJI));
            }
        });
    }

    @Override
    public void saveToHistory(final Grammar grammar) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                History history = realm.where(History.class).findFirst();
                if (history == null) {
                    history = realm.createObject(History.class);
                }
                Gson gson = CustomGson.getGson();
                String json = gson.toJson(grammar);
                history.getPrimaryKey().add(0, new RealmString(json));
                history.getType().add(0, new RealmInteger(INT_GRAMMAR));
            }
        });
    }

    @Override
    public History getHistory() {
        History history = mRealm.where(History.class).findFirstAsync();
        return history;
    }

    @Override
    public Observable<Boolean> changeLikeState(final int type, final String key) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Boolean> e)
                throws Exception {
                mRealm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        TemporaryBox box = realm.where(TemporaryBox.class).findFirst();
                        if (box == null) {
                            box = realm.createObject(TemporaryBox.class);
                        }
                        if (box.getPrimaryKey().contains(key)) {
                            for (int i = 0; i < box.getPrimaryKey().size(); i++) {
                                if (box.getPrimaryKey().get(i).equals(key)) {
                                    box.getPrimaryKey().remove(i);
                                    box.getType().remove(i);
                                    break;
                                }
                            }
                            e.onNext(false);
                        } else {
                            box.getPrimaryKey().add(0, new RealmString(key));
                            box.getType().add(0, new RealmInteger(type));
                            e.onNext(true);
                        }
                        e.onComplete();
                    }
                });
            }
        });
    }

    @Override
    public Observable<Boolean> isLiked(final String key) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Boolean> e)
                throws Exception {
                mRealm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        TemporaryBox box = realm.where(TemporaryBox.class).findFirst();
                        if (box == null) {
                            box = realm.createObject(TemporaryBox.class);
                        }
                        if (box.getPrimaryKey().contains(key)) {
                            e.onNext(true);
                        } else {
                            e.onNext(false);
                        }
                        e.onComplete();
                    }
                });
            }
        });
    }
}
