package com.tmd.dictionary.screen.fragment.viedetail;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;

import com.tmd.dictionary.BR;
import com.tmd.dictionary.data.model.VieBox;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.screen.OpenableMoveToBoxFrag;
import com.tmd.dictionary.screen.activity.main.MainContract;
import com.tmd.dictionary.screen.activity.main.MainViewModel;

import io.realm.RealmResults;

/**
 * Exposes the data to be used in the VieDetail screen.
 */
public class VieDetailViewModel extends BaseObservable
    implements VieDetailContract.ViewModel, OpenableMoveToBoxFrag {
    private MainContract.ViewModel mMainViewModel;
    private VieDetailContract.Presenter mPresenter;
    private VieWord mVieWord;
    private boolean mIsLiked;

    public VieDetailViewModel(MainContract.ViewModel mainViewModel, VieWord vieWord) {
        mMainViewModel = mainViewModel;
        mVieWord = vieWord;
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
        mPresenter.isLiked(mVieWord);
        mPresenter.saveToHistory(mVieWord);
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(VieDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onChangeLikeState() {
        mPresenter.changeLikeState(mVieWord);
    }

    @Override
    public void onSetLiked(Boolean isLiked) {
        mIsLiked = isLiked;
        notifyPropertyChanged(BR.liked);
    }

    @Override
    public void onOpenMoveToBoxFragment() {
        mMainViewModel.onOpenMoveToBoxFragment(this);
    }

    @Override
    public void onMoveToBox(VieBox vieBox, VieWord vieWord) {
        mPresenter.moveToBox(vieBox, vieWord);
    }

    public void onOpenSearchFragment(String needSearch) {
        mMainViewModel.onOpenSearchFragment(needSearch);
    }

    @Override
    public Context getContext() {
        return ((MainViewModel) mMainViewModel).getContext();
    }

    @Override
    public String getWordOrigin() {
        return mVieWord.getOrigin();
    }

    @Override
    public RealmResults getAllFlashcardBoxes() {
        return mPresenter.getAllFlashcardBoxes();
    }

    @Override
    public void createBox(String name, String description) {
        mPresenter.createFlashcardBox(new VieBox(name, description));
    }

    /**
     * Parcelable
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mMainViewModel, i);
        parcel.writeParcelable(mVieWord, i);
        parcel.writeByte((byte) (mIsLiked ? 1 : 0));
    }

    protected VieDetailViewModel(Parcel in) {
        mMainViewModel = in.readParcelable(MainContract.ViewModel.class.getClassLoader());
        mVieWord = in.readParcelable(VieWord.class.getClassLoader());
        mIsLiked = in.readByte() != 0;
    }

    public static final Creator<VieDetailViewModel> CREATOR = new Creator<VieDetailViewModel>() {
        @Override
        public VieDetailViewModel createFromParcel(Parcel in) {
            return new VieDetailViewModel(in);
        }

        @Override
        public VieDetailViewModel[] newArray(int size) {
            return new VieDetailViewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
