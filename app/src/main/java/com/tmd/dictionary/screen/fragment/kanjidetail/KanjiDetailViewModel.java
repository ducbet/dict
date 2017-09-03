package com.tmd.dictionary.screen.fragment.kanjidetail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tmd.dictionary.BR;
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
public class KanjiDetailViewModel extends BaseObservable implements KanjiDetailContract.ViewModel {
    private MainContract.ViewModel mMainViewModel;
    private KanjiDetailContract.Presenter mPresenter;
    private Kanji mKanji;
    private Realm mRealm;
    private boolean mIsLiked;

    public KanjiDetailViewModel(MainContract.ViewModel mainViewModel, Kanji kanji) {
        mMainViewModel = mainViewModel;
        mKanji = kanji;
    }

    public Kanji getKanji() {
        return mKanji;
    }

    @Bindable
    public boolean isLiked() {
        return mIsLiked;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.isLiked(mKanji.getOrigin());
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

    @Override
    public void onInitRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder()
            .schemaVersion(SCHEMA_VERSION)
            .assetFile(DictApplication.getContext().getString(R.string.database_name))
            .build();
        mRealm = Realm.getInstance(config);
    }

    @Override
    public void onCloseRealm() {
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();
        }
    }

    @Override
    public void onChangeLikeState() {
        mPresenter.changeLikeState(mRealm, mKanji.getOrigin());
    }

    @Override
    public void onSetLiked(Boolean isLiked) {
        mIsLiked = isLiked;
        notifyPropertyChanged(BR.liked);
    }
}
