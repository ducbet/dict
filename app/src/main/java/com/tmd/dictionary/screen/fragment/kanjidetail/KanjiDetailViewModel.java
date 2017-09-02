package com.tmd.dictionary.screen.fragment.kanjidetail;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.screen.activity.main.MainContract;
import com.tmd.dictionary.util.DictApplication;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.tmd.dictionary.staticfinal.ConstantValue.SCHEMA_VERSION;

/**
 * Exposes the data to be used in the KanjiDetail screen.
 */
public class KanjiDetailViewModel implements KanjiDetailContract.ViewModel {
    private MainContract.ViewModel mMainViewModel;
    private KanjiDetailContract.Presenter mPresenter;
    private Kanji mKanji;
    private Realm mRealm;

    public KanjiDetailViewModel(MainContract.ViewModel mainViewModel, Kanji kanji) {
        mMainViewModel = mainViewModel;
        mKanji = kanji;
        initRealm();
    }

    private void initRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder()
            .schemaVersion(SCHEMA_VERSION)
            .assetFile(DictApplication.getContext().getString(R.string.database_name))
            .build();
        mRealm = Realm.getInstance(config);
    }

    public Kanji getKanji() {
        return mKanji;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.saveToHistory(mRealm, mKanji.getOrigin());
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        mRealm.close();
    }

    @Override
    public void setPresenter(KanjiDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
