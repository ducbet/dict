package com.tmd.dictionary.data.source.local;

import com.google.gson.Gson;
import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.GrammarBox;
import com.tmd.dictionary.data.model.History;
import com.tmd.dictionary.data.model.JpnBox;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.KanjiBox;
import com.tmd.dictionary.data.model.LikedWord;
import com.tmd.dictionary.data.model.RealmInteger;
import com.tmd.dictionary.data.model.RealmString;
import com.tmd.dictionary.data.model.VieBox;
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
    public void createHistoryObjectIfNotExist() {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                History history = realm.where(History.class).findFirst();
                if (history == null) {
                    realm.createObject(History.class);
                }
            }
        });
    }

    @Override
    public void saveToHistory(final JpnWord jpnWord) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                History history = realm.where(History.class).findFirst();
                Gson gson = CustomGson.getGson();
                String json = gson.toJson(jpnWord);
                history.getJsonWord().add(0, new RealmString(json));
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
                Gson gson = CustomGson.getGson();
                String json = gson.toJson(vieWord);
                history.getJsonWord().add(0, new RealmString(json));
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
                Gson gson = CustomGson.getGson();
                String json = gson.toJson(kanji);
                history.getJsonWord().add(0, new RealmString(json));
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
                Gson gson = CustomGson.getGson();
                String json = gson.toJson(grammar);
                history.getJsonWord().add(0, new RealmString(json));
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
    public LikedWord getLikedWords() {
        LikedWord likedWords = mRealm.where(LikedWord.class).findFirstAsync();
        return likedWords;
    }

    @Override
    public void createLikedWordObjectIfNotExist() {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                LikedWord likedWord = realm.where(LikedWord.class).findFirst();
                if (likedWord == null) {
                    realm.createObject(LikedWord.class);
                }
            }
        });
    }

    @Override
    public Observable<Boolean> changeLikeState(final JpnWord jpnWord) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Boolean> e)
                throws Exception {
                mRealm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        LikedWord likedWords = realm.where(LikedWord.class).findFirst();
                        Gson gson = CustomGson.getGson();
                        String json = gson.toJson(jpnWord);
                        if (likedWords.getJsonWord().contains(json)) {
                            for (int i = 0; i < likedWords.getJsonWord().size(); i++) {
                                if (likedWords.getJsonWord().get(i).equals(json)) {
                                    likedWords.getJsonWord().remove(i);
                                    likedWords.getType().remove(i);
                                    break;
                                }
                            }
                            e.onNext(false);
                        } else {
                            likedWords.getJsonWord().add(0, new RealmString(json));
                            likedWords.getType().add(0, new RealmInteger(INT_JPN_WORD));
                            e.onNext(true);
                        }
                        e.onComplete();
                    }
                });
            }
        });
    }

    @Override
    public Observable<Boolean> changeLikeState(final VieWord vieWord) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Boolean> e)
                throws Exception {
                mRealm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        LikedWord likedWords = realm.where(LikedWord.class).findFirst();
                        Gson gson = CustomGson.getGson();
                        String json = gson.toJson(vieWord);
                        if (likedWords.getJsonWord().contains(json)) {
                            for (int i = 0; i < likedWords.getJsonWord().size(); i++) {
                                if (likedWords.getJsonWord().get(i).equals(json)) {
                                    likedWords.getJsonWord().remove(i);
                                    likedWords.getType().remove(i);
                                    break;
                                }
                            }
                            e.onNext(false);
                        } else {
                            likedWords.getJsonWord().add(0, new RealmString(json));
                            likedWords.getType().add(0, new RealmInteger(INT_VIE_WORD));
                            e.onNext(true);
                        }
                        e.onComplete();
                    }
                });
            }
        });
    }

    @Override
    public Observable<Boolean> changeLikeState(final Kanji kanji) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Boolean> e)
                throws Exception {
                mRealm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        LikedWord likedWords = realm.where(LikedWord.class).findFirst();
                        Gson gson = CustomGson.getGson();
                        String json = gson.toJson(kanji);
                        if (likedWords.getJsonWord().contains(json)) {
                            for (int i = 0; i < likedWords.getJsonWord().size(); i++) {
                                if (likedWords.getJsonWord().get(i).equals(json)) {
                                    likedWords.getJsonWord().remove(i);
                                    likedWords.getType().remove(i);
                                    break;
                                }
                            }
                            e.onNext(false);
                        } else {
                            likedWords.getJsonWord().add(0, new RealmString(json));
                            likedWords.getType().add(0, new RealmInteger(INT_KANJI));
                            e.onNext(true);
                        }
                        e.onComplete();
                    }
                });
            }
        });
    }

    @Override
    public Observable<Boolean> changeLikeState(final Grammar grammar) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Boolean> e)
                throws Exception {
                mRealm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        LikedWord likedWords = realm.where(LikedWord.class).findFirst();
                        Gson gson = CustomGson.getGson();
                        String json = gson.toJson(grammar);
                        if (likedWords.getJsonWord().contains(json)) {
                            for (int i = 0; i < likedWords.getJsonWord().size(); i++) {
                                if (likedWords.getJsonWord().get(i).equals(json)) {
                                    likedWords.getJsonWord().remove(i);
                                    likedWords.getType().remove(i);
                                    break;
                                }
                            }
                            e.onNext(false);
                        } else {
                            likedWords.getJsonWord().add(0, new RealmString(json));
                            likedWords.getType().add(0, new RealmInteger(INT_GRAMMAR));
                            e.onNext(true);
                        }
                        e.onComplete();
                    }
                });
            }
        });
    }

    @Override
    public Observable<Boolean> isLiked(final JpnWord jpnWord) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Boolean> e)
                throws Exception {
                mRealm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        LikedWord likedWord = realm.where(LikedWord.class).findFirst();
                        Gson gson = CustomGson.getGson();
                        String json = gson.toJson(jpnWord);
                        if (likedWord.getJsonWord().contains(json)) {
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

    @Override
    public Observable<Boolean> isLiked(final VieWord vieWord) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Boolean> e)
                throws Exception {
                mRealm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        LikedWord likedWord = realm.where(LikedWord.class).findFirst();
                        Gson gson = CustomGson.getGson();
                        String json = gson.toJson(vieWord);
                        if (likedWord.getJsonWord().contains(json)) {
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

    @Override
    public Observable<Boolean> isLiked(final Kanji kanji) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Boolean> e)
                throws Exception {
                mRealm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        LikedWord likedWord = realm.where(LikedWord.class).findFirst();
                        Gson gson = CustomGson.getGson();
                        String json = gson.toJson(kanji);
                        if (likedWord.getJsonWord().contains(json)) {
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

    @Override
    public Observable<Boolean> isLiked(final Grammar grammar) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Boolean> e)
                throws Exception {
                mRealm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        LikedWord likedWord = realm.where(LikedWord.class).findFirst();
                        Gson gson = CustomGson.getGson();
                        String json = gson.toJson(grammar);
                        if (likedWord.getJsonWord().contains(json)) {
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

    private boolean isBoxExits(JpnBox box) {
        return mRealm.where(JpnBox.class)
            .equalTo("name.value", box.getName().getValue())
            .findFirst() != null;
    }

    @Override
    public void createFlashcardBox(final JpnBox newBox) {
        if (isBoxExits(newBox)) {
            return;
        }
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(newBox);
            }
        });
    }

    private boolean isBoxExits(VieBox box) {
        return mRealm.where(VieBox.class)
            .equalTo("name.value", box.getName().getValue())
            .findFirst() != null;
    }

    @Override
    public void createFlashcardBox(final VieBox newBox) {
        if (isBoxExits(newBox)) {
            return;
        }
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(newBox);
            }
        });
    }

    private boolean isBoxExits(KanjiBox box) {
        return mRealm.where(KanjiBox.class)
            .equalTo("name.value", box.getName().getValue())
            .findFirst() != null;
    }

    @Override
    public void createFlashcardBox(final KanjiBox newBox) {
        if (isBoxExits(newBox)) {
            return;
        }
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(newBox);
            }
        });
    }

    private boolean isBoxExits(GrammarBox box) {
        return mRealm.where(GrammarBox.class)
            .equalTo("name.value", box.getName().getValue())
            .findFirst() != null;
    }

    @Override
    public void createFlashcardBox(final GrammarBox newBox) {
        if (isBoxExits(newBox)) {
            return;
        }
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(newBox);
            }
        });
    }

    @Override
    public RealmResults<JpnBox> getAllJpnBoxes() {
        return mRealm.where(JpnBox.class).findAllAsync();
    }

    @Override
    public RealmResults<VieBox> getAllVieBoxes() {
        return mRealm.where(VieBox.class).findAllAsync();
    }

    @Override
    public RealmResults<KanjiBox> getAllKanjiBoxes() {
        return mRealm.where(KanjiBox.class).findAllAsync();
    }

    @Override
    public RealmResults<GrammarBox> getAllGrammarBoxes() {
        return mRealm.where(GrammarBox.class).findAllAsync();
    }

    @Override
    public void moveToBox(final JpnBox jpnBox, final JpnWord jpnWord) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                jpnBox.getWords().add(jpnWord);
            }
        });
    }

    @Override
    public void moveToBox(final VieBox vieBox, final VieWord vieWord) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                vieBox.getWords().add(vieWord);
            }
        });
    }

    @Override
    public void moveToBox(final KanjiBox kanjiBox, final Kanji kanji) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                kanjiBox.getKanjis().add(kanji);
            }
        });
    }

    @Override
    public void moveToBox(final GrammarBox grammarBox, final Grammar grammar) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                grammarBox.getGrammars().add(grammar);
            }
        });
    }
}
