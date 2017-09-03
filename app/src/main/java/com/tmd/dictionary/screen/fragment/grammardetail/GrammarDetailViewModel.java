package com.tmd.dictionary.screen.fragment.grammardetail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tmd.dictionary.BR;
import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.screen.activity.main.MainContract;
import com.tmd.dictionary.util.DictApplication;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.tmd.dictionary.staticfinal.ConstantValue.SCHEMA_VERSION;

/**
 * Exposes the data to be used in the GrammarDetail screen.
 */
public class GrammarDetailViewModel extends BaseObservable
    implements GrammarDetailContract.ViewModel {
    private MainContract.ViewModel mMainViewModel;
    private GrammarDetailContract.Presenter mPresenter;
    private Grammar mGrammar;
    private Realm mRealm;
    private boolean mIsLiked;

    public GrammarDetailViewModel(MainContract.ViewModel mainViewModel, Grammar grammar) {
        mMainViewModel = mainViewModel;
        mGrammar = grammar;
        initRealm();
    }

    private void initRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder()
            .schemaVersion(SCHEMA_VERSION)
            .assetFile(DictApplication.getContext().getString(R.string.database_name))
            .build();
        mRealm = Realm.getInstance(config);
    }

    public Grammar getGrammar() {
        return mGrammar;
    }

    @Bindable
    public boolean isLiked() {
        return mIsLiked;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.isLiked(mGrammar.getOrigin());
        mPresenter.saveToHistory(mRealm, mGrammar.getOrigin());
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        mRealm.close();
    }

    @Override
    public void setPresenter(GrammarDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onChangeLikeState() {
        mPresenter.changeLikeState(mRealm, mGrammar.getOrigin());
    }

    @Override
    public void onSetLiked(Boolean isLiked) {
        mIsLiked = isLiked;
        notifyPropertyChanged(BR.liked);
    }
}
