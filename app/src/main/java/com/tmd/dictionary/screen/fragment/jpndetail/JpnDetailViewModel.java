package com.tmd.dictionary.screen.fragment.jpndetail;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;

import com.tmd.dictionary.BR;
import com.tmd.dictionary.data.model.JpnBox;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.screen.OnClickSearchedItemListener;
import com.tmd.dictionary.screen.OpenableMoveToBoxFrag;
import com.tmd.dictionary.screen.activity.main.MainContract;
import com.tmd.dictionary.screen.activity.main.MainViewModel;

import io.realm.RealmResults;

/**
 * Exposes the data to be used in the JpnWordDetail screen.
 */
public class JpnDetailViewModel extends BaseObservable
    implements JpnDetailContract.ViewModel, OnClickSearchedItemListener, OpenableMoveToBoxFrag {
    private MainContract.ViewModel mMainViewModel;
    private JpnDetailContract.Presenter mPresenter;
    private JpnWord mJpnWord;
    private JpnDetailKanjisAdapter mJpnDetailKanjisAdapter;
    private JpnDetailExamplesAdapter mJpnDetailExamplesAdapter;
    private boolean mIsLiked;

    public JpnDetailViewModel(MainContract.ViewModel mainViewModel, JpnWord jpnWord) {
        mMainViewModel = mainViewModel;
        mJpnWord = jpnWord;
        mJpnDetailKanjisAdapter = new JpnDetailKanjisAdapter(this, jpnWord.getKanjis());
        mJpnDetailExamplesAdapter = new JpnDetailExamplesAdapter(this, jpnWord.getExamples());
    }

    public JpnWord getJpnWord() {
        return mJpnWord;
    }

    @Bindable
    public boolean isLiked() {
        return mIsLiked;
    }

    public JpnDetailKanjisAdapter getJpnDetailKanjisAdapter() {
        return mJpnDetailKanjisAdapter;
    }

    public JpnDetailExamplesAdapter getJpnDetailExamplesAdapter() {
        return mJpnDetailExamplesAdapter;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.isLiked(mJpnWord);
        mPresenter.saveToHistory(mJpnWord);
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(JpnDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onChangeLikeState() {
        mPresenter.changeLikeState(mJpnWord);
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

    @Override
    public void onClick(Object item) {
        if (item instanceof Kanji) {
            mMainViewModel.onOpenKanjiDetailFragment((Kanji) item);
            return;
        }
    }

    /**
     * Parcelable
     */
    protected JpnDetailViewModel(Parcel in) {
        mMainViewModel = in.readParcelable(MainContract.ViewModel.class.getClassLoader());
        mJpnWord = in.readParcelable(JpnWord.class.getClassLoader());
        mIsLiked = in.readByte() != 0;
    }

    public static final Creator<JpnDetailViewModel> CREATOR = new Creator<JpnDetailViewModel>() {
        @Override
        public JpnDetailViewModel createFromParcel(Parcel in) {
            return new JpnDetailViewModel(in);
        }

        @Override
        public JpnDetailViewModel[] newArray(int size) {
            return new JpnDetailViewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mMainViewModel, i);
        parcel.writeParcelable(mJpnWord, i);
        parcel.writeByte((byte) (mIsLiked ? 1 : 0));
    }

    @Override
    public Context getContext() {
        return ((MainViewModel) mMainViewModel).getContext();
    }

    @Override
    public String getWordOrigin() {
        return mJpnWord.getOrigin();
    }

    @Override
    public RealmResults getAllFlashcardBoxes() {
        return mPresenter.getAllFlashcardBoxes();
    }

    @Override
    public void createBox(String name, String description) {
        mPresenter.createFlashcardBox(new JpnBox(name, description));
    }
}
