package com.tmd.dictionary.screen.fragment.viedetail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tmd.dictionary.BR;
import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.screen.activity.main.MainContract;
import com.tmd.dictionary.util.DictApplication;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.tmd.dictionary.staticfinal.ConstantValue.SCHEMA_VERSION;

/**
 * Exposes the data to be used in the VieDetail screen.
 */
public class VieDetailViewModel extends BaseObservable implements VieDetailContract.ViewModel {
    private MainContract.ViewModel mMainViewModel;
    private VieDetailContract.Presenter mPresenter;
    private VieWord mVieWord;
    private Realm mRealm;
    private boolean mIsLiked;

    public VieDetailViewModel(MainContract.ViewModel mainViewModel, VieWord vieWord) {
        mMainViewModel = mainViewModel;
        mVieWord = vieWord;
        initRealm();
    }

    private void initRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder()
            .schemaVersion(SCHEMA_VERSION)
            .assetFile(DictApplication.getContext().getString(R.string.database_name))
            .build();
        mRealm = Realm.getInstance(config);
    }

    public VieWord getVieWord() {
        return mVieWord;
    }

    @Bindable
    public boolean isLiked() {
        return mIsLiked;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.isLiked(mVieWord.getOrigin());
        mPresenter.saveToHistory(mRealm, mVieWord.getOrigin());
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        mRealm.close();
    }

    @Override
    public void setPresenter(VieDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onChangeLikeState() {
        mPresenter.changeLikeState(mRealm, mVieWord.getOrigin());
    }

    @Override
    public void onSetLiked(Boolean isLiked) {
        mIsLiked = isLiked;
        notifyPropertyChanged(BR.liked);
    }
}
