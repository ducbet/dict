package com.tmd.dictionary.data.source.local;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.History;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.RealmInteger;
import com.tmd.dictionary.data.model.RealmString;
import com.tmd.dictionary.data.model.TemporaryBox;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.data.source.DataSource;
import com.tmd.dictionary.util.DictApplication;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static com.tmd.dictionary.staticfinal.ConstantValue.SCHEMA_VERSION;

/**
 * Created by tmd on 09/07/2017.
 */
public class _CRUDHelper implements DataSource {
    private static RealmAsyncTask mRealmAsyncTask;
    private Realm mRealm;

    private void getRealmInstance() {
        RealmConfiguration config = new RealmConfiguration.Builder()
            .schemaVersion(SCHEMA_VERSION)
            .assetFile(DictApplication.getContext().getString(R.string.database_name))
            .build();
        mRealm = Realm.getInstance(config);
    }

    @Override
    public RealmResults<JpnWord> searchJpnWordHasKanjis(final String input) {
        getRealmInstance();
        RealmResults<JpnWord> jpnWords = mRealm.where(JpnWord.class)
            .like("origin", "*" + input + "*")
            .findAllAsync();
        mRealm.close();
        return jpnWords;
    }

    @Override
    public RealmResults<JpnWord> searchJpnWordNotHasKanjis(String input) {
        getRealmInstance();
        RealmResults<JpnWord> jpnWords = mRealm.where(JpnWord.class)
            .like("kana", "*" + input + "*")
            .findAllAsync();
        mRealm.close();
        return jpnWords;
    }

    @Override
    public RealmResults<VieWord> searchVieJpn(final String input) {
        getRealmInstance();
        RealmResults<VieWord> vieWords = mRealm.where(VieWord.class)
            .like("origin", "*" + input + "*")
            .or()
            .like("kana", "*" + input + "*")
            .findAllAsync();
        mRealm.close();
        return vieWords;
    }

    @Override
    public Observable<RealmResults<Kanji>> searchKanji(final String input) {
        // SELECT * FROM kanji_main WHERE kanji = ?
        return Observable.create(new ObservableOnSubscribe<RealmResults<Kanji>>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<RealmResults<Kanji>> e)
                throws Exception {
                RealmConfiguration config = new RealmConfiguration.Builder()
                    .schemaVersion(SCHEMA_VERSION)
                    .assetFile(DictApplication.getContext().getString(R.string.database_name))
                    .build();
                Realm realm = Realm.getInstance(config);
                realm.executeTransaction(new Realm.Transaction() {
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
                realm.close();
            }
        });
    }

    @Override
    public Observable<RealmResults<Grammar>> searchGrammar(final String input) {
        return Observable.create(new ObservableOnSubscribe<RealmResults<Grammar>>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<RealmResults<Grammar>> e)
                throws Exception {
                RealmConfiguration config = new RealmConfiguration.Builder()
                    .schemaVersion(SCHEMA_VERSION)
                    .assetFile(DictApplication.getContext().getString(R.string.database_name))
                    .build();
                Realm realm = Realm.getInstance(config);
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<Grammar> grammars = realm.where(Grammar.class)
                            .like("origin", "*" + input + "*")
                            .findAll();
                        e.onNext(grammars);
                        e.onComplete();
                    }
                });
                realm.close();
            }
        });
    }

    public RealmResults<JpnWord> chaningJpnQuery(String input,
                                                 RealmResults<JpnWord> parentsResult) {
        getRealmInstance();
        RealmResults<JpnWord> jpnWords = parentsResult.where()
            .like("origin", "*" + input + "*")
            .or()
            .like("kana", "*" + input + "*")
            .findAllAsync();
        mRealm.close();
        return jpnWords;
    }

    @Override
    public RealmResults<VieWord> chaningVieQuery(String input,
                                                 RealmResults<VieWord> parentsResult) {
        getRealmInstance();
        RealmResults<VieWord> vieWords = parentsResult.where()
            .like("origin", "*" + input + "*")
            .or()
            .like("kana", "*" + input + "*")
            .findAllAsync();
        mRealm.close();
        return vieWords;
    }

    @Override
    public void saveToHistory(Realm realm, final int type, final String key) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                History history = realm.where(History.class).findFirst();
                if (history == null) {
                    history = realm.createObject(History.class);
                }
                history.getPrimaryKey().add(0, new RealmString(key));
                history.getType().add(0, new RealmInteger(type));
            }
        });
    }

    @Override
    public Observable<History> getHistory() {
        return Observable.create(new ObservableOnSubscribe<History>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<History> e) throws Exception {
                RealmConfiguration config = new RealmConfiguration.Builder()
                    .schemaVersion(SCHEMA_VERSION)
                    .assetFile(DictApplication.getContext().getString(R.string.database_name))
                    .build();
                Realm realm = Realm.getInstance(config);
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        History history = realm.where(History.class).findFirst();
                        if (history == null) {
                            history = realm.createObject(History.class);
                        }
                        e.onNext(history);
                    }
                });
                realm.close();
            }
        });
    }

    @Override
    public Observable<Boolean> changeLikeState(final int type, final String key) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Boolean> e)
                throws Exception {
                RealmConfiguration config = new RealmConfiguration.Builder()
                    .schemaVersion(SCHEMA_VERSION)
                    .assetFile(DictApplication.getContext().getString(R.string.database_name))
                    .build();
                Realm realm = Realm.getInstance(config);
                realm.executeTransactionAsync(new Realm.Transaction() {
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
                realm.close();
            }
        });
    }

    @Override
    public Observable<Boolean> isLiked(final String key) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Boolean> e)
                throws Exception {
                RealmConfiguration config = new RealmConfiguration.Builder()
                    .schemaVersion(SCHEMA_VERSION)
                    .assetFile(DictApplication.getContext().getString(R.string.database_name))
                    .build();
                Realm realm = Realm.getInstance(config);
                realm.executeTransactionAsync(new Realm.Transaction() {
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
                realm.close();
            }
        });
    }
}
