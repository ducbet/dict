package com.tmd.dictionary.screen.fragment.kanjidetail;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;

import com.tmd.dictionary.BR;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.KanjiBox;
import com.tmd.dictionary.screen.OpenableMoveToBoxFrag;
import com.tmd.dictionary.screen.activity.main.MainContract;
import com.tmd.dictionary.screen.activity.main.MainViewModel;

import io.realm.RealmResults;

/**
 * Exposes the data to be used in the KanjiDetail screen.
 */
public class KanjiDetailViewModel extends BaseObservable
    implements KanjiDetailContract.ViewModel, OpenableMoveToBoxFrag {
    private MainContract.ViewModel mMainViewModel;
    private KanjiDetailContract.Presenter mPresenter;
    private Kanji mKanji;
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
        mPresenter.isLiked(mKanji);
        mPresenter.saveToHistory(mKanji);
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(KanjiDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onChangeLikeState() {
        mPresenter.changeLikeState(mKanji);
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
    protected KanjiDetailViewModel(Parcel in) {
        mMainViewModel = in.readParcelable(MainContract.ViewModel.class.getClassLoader());
        mKanji = in.readParcelable(Kanji.class.getClassLoader());
        mIsLiked = in.readByte() != 0;
    }

    public static final Creator<KanjiDetailViewModel> CREATOR =
        new Creator<KanjiDetailViewModel>() {
            @Override
            public KanjiDetailViewModel createFromParcel(Parcel in) {
                return new KanjiDetailViewModel(in);
            }

            @Override
            public KanjiDetailViewModel[] newArray(int size) {
                return new KanjiDetailViewModel[size];
            }
        };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mMainViewModel, i);
        parcel.writeParcelable(mKanji, i);
        parcel.writeByte((byte) (mIsLiked ? 1 : 0));
    }

    @Override
    public Context getContext() {
        return ((MainViewModel) mMainViewModel).getContext();
    }

    @Override
    public String getWordOrigin() {
        return mKanji.getOrigin();
    }

    @Override
    public RealmResults getAllFlashcardBoxes() {
        return mPresenter.getAllFlashcardBoxes();
    }

    @Override
    public void createBox(String name, String description) {
        mPresenter.createFlashcardBox(new KanjiBox(name, description));
    }
}
