package com.tmd.dictionary.data.source.local;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.History;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.RealmInteger;
import com.tmd.dictionary.data.model.RealmString;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.data.source.DataSource;
import com.tmd.dictionary.util.DictApplication;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static com.tmd.dictionary.staticfinal.ConstantValue.SCHEMA_VERSION;

/**
 * Created by tmd on 09/07/2017.
 */
public class _CRUDHelper implements DataSource {
    @Override
    public Observable<RealmResults<JpnWord>> searchJpnVie(final String input) {
        // SELECT * FROM jpn_vie_main WHERE c0origin LIKE ?
        // OR c1kana LIKE ? ORDER BY c3priority DESC LIMIT 100
        return Observable.create(new ObservableOnSubscribe<RealmResults<JpnWord>>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<RealmResults<JpnWord>> e)
                throws Exception {
                RealmConfiguration config = new RealmConfiguration.Builder()
                    .schemaVersion(SCHEMA_VERSION)
                    .assetFile(DictApplication.getContext().getString(R.string.database_name))
                    .build();
                Realm realm = Realm.getInstance(config);
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<JpnWord> jpnWords = realm.where(JpnWord.class)
                            .like("origin", "*" + input + "*")
                            .or()
                            .like("kana", "*" + input + "*")
                            .findAll();
                        e.onNext(jpnWords);
                        e.onComplete();
                    }
                });
                realm.close();
            }
        });
    }

    @Override
    public Observable<RealmResults<VieWord>> searchVieJpn(final String input) {
        // TODO: 30/08/2017  nếu không có .subscribeOn(Schedulers.computation())
        // trong VieJavPresenter thì vẫn là thực hiện ở main thread
        return Observable.create(new ObservableOnSubscribe<RealmResults<VieWord>>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<RealmResults<VieWord>> e)
                throws Exception {
                RealmConfiguration config = new RealmConfiguration.Builder()
                    .schemaVersion(SCHEMA_VERSION)
                    .assetFile(DictApplication.getContext().getString(R.string.database_name))
                    .build();
                Realm realm = Realm.getInstance(config);
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<VieWord> vieWords = realm.where(VieWord.class)
                            .like("origin", "*" + input + "*")
                            .or()
                            .like("kana", "*" + input + "*")
                            .findAll();
                        e.onNext(vieWords);
                        e.onComplete();
                    }
                });
                realm.close();
            }
        });
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

    @Override
    public void saveToHistory(Realm realm, final int type, final String primaryKey) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                History history = realm.where(History.class).findFirst();
                if (history == null) {
                    history = realm.createObject(History.class);
                }
                history.getPrimaryKey().add(0, new RealmString(primaryKey));
                history.getType().add(0, new RealmInteger(type));
            }
        });
    }
}
