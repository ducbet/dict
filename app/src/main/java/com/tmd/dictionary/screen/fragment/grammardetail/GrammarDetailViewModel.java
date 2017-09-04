package com.tmd.dictionary.screen.fragment.grammardetail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tmd.dictionary.BR;
import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.screen.activity.main.MainContract;

/**
 * Exposes the data to be used in the GrammarDetail screen.
 */
public class GrammarDetailViewModel extends BaseObservable
    implements GrammarDetailContract.ViewModel {
    private MainContract.ViewModel mMainViewModel;
    private GrammarDetailContract.Presenter mPresenter;
    private Grammar mGrammar;
    private boolean mIsLiked;

    public GrammarDetailViewModel(MainContract.ViewModel mainViewModel, Grammar grammar) {
        mMainViewModel = mainViewModel;
        mGrammar = grammar;
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
        mPresenter.saveToHistory(mGrammar.getOrigin());
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(GrammarDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onChangeLikeState() {
        mPresenter.changeLikeState(mGrammar.getOrigin());
    }

    @Override
    public void onSetLiked(Boolean isLiked) {
        mIsLiked = isLiked;
        notifyPropertyChanged(BR.liked);
    }
}
