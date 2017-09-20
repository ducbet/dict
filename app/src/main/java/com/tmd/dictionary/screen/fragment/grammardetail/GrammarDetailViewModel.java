package com.tmd.dictionary.screen.fragment.grammardetail;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;

import com.tmd.dictionary.BR;
import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.GrammarBox;
import com.tmd.dictionary.screen.OpenableMoveToBoxFrag;
import com.tmd.dictionary.screen.activity.main.MainContract;
import com.tmd.dictionary.screen.activity.main.MainViewModel;

import io.realm.RealmResults;

/**
 * Exposes the data to be used in the GrammarDetail screen.
 */
public class GrammarDetailViewModel extends BaseObservable
    implements GrammarDetailContract.ViewModel, OpenableMoveToBoxFrag {
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
        mPresenter.isLiked(mGrammar);
        mPresenter.saveToHistory(mGrammar);
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
        mPresenter.changeLikeState(mGrammar);
    }

    @Override
    public void onSetLiked(Boolean isLiked) {
        mIsLiked = isLiked;
        notifyPropertyChanged(BR.liked);
    }

    @Override
    public void onMoveToBox() {
        mMainViewModel.onMoveToBoxFragment(this);
    }

    /**
     * Parcelable
     */
    protected GrammarDetailViewModel(Parcel in) {
        mMainViewModel = in.readParcelable(MainContract.ViewModel.class.getClassLoader());
        mGrammar = in.readParcelable(Grammar.class.getClassLoader());
        mIsLiked = in.readByte() != 0;
    }

    public static final Creator<GrammarDetailViewModel> CREATOR =
        new Creator<GrammarDetailViewModel>() {
            @Override
            public GrammarDetailViewModel createFromParcel(Parcel in) {
                return new GrammarDetailViewModel(in);
            }

            @Override
            public GrammarDetailViewModel[] newArray(int size) {
                return new GrammarDetailViewModel[size];
            }
        };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mMainViewModel, i);
        parcel.writeParcelable(mGrammar, i);
        parcel.writeByte((byte) (mIsLiked ? 1 : 0));
    }

    @Override
    public Context getContext() {
        return ((MainViewModel) mMainViewModel).getContext();
    }

    @Override
    public String getWordOrigin() {
        return mGrammar.getOrigin();
    }

    @Override
    public RealmResults getAllFlashcardBoxes() {
        return mPresenter.getAllFlashcardBoxes();
    }

    @Override
    public void createBox(String name, String description) {
        mPresenter.createFlashcardBox(new GrammarBox(name, description));
    }
}
