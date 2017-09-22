package com.tmd.dictionary.screen.fragment.listboxes;

import android.os.Parcel;

import com.tmd.dictionary.data.model.GrammarBox;
import com.tmd.dictionary.data.model.JpnBox;
import com.tmd.dictionary.data.model.KanjiBox;
import com.tmd.dictionary.data.model.VieBox;
import com.tmd.dictionary.screen.activity.boxs.BoxesContract;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

import static com.tmd.dictionary.staticfinal.ConstantValue.INT_GRAMMAR;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_JPN_WORD;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_KANJI;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_VIE_WORD;

/**
 * Exposes the data to be used in the JpnBoxes screen.
 */
public class ListBoxesViewModel implements ListBoxesContract.ViewModel {
    private BoxesContract.ViewModel mBoxesViewModel;
    private ListBoxesContract.Presenter mPresenter;
    private int mBoxType;
    private ListBoxesAdapter mAdapter;
    private RealmChangeListener mRealmChangeListener =
        new RealmChangeListener<RealmResults>() {
            @Override
            public void onChange(RealmResults boxes) {
                mAdapter.setSource(boxes);
            }
        };
    private Realm mRealm;

    public ListBoxesViewModel(BoxesContract.ViewModel boxesViewModel, int boxType, Realm realm) {
        mBoxesViewModel = boxesViewModel;
        mBoxType = boxType;
        mAdapter = new ListBoxesAdapter(this);
        mRealm = realm;
    }

    public Realm getRealm() {
        return mRealm;
    }

    public ListBoxesAdapter getAdapter() {
        return mAdapter;
    }

    private void getAllFlashcardBoxes() {
        switch (mBoxType) {
            case INT_JPN_WORD:
                mPresenter.getAllJpnBoxes().addChangeListener(mRealmChangeListener);
                break;
            case INT_VIE_WORD:
                mPresenter.getAllVieBoxes().addChangeListener(mRealmChangeListener);
                break;
            case INT_KANJI:
                mPresenter.getAllKanjiBoxes().addChangeListener(mRealmChangeListener);
                break;
            case INT_GRAMMAR:
                mPresenter.getAllGrammarBoxes().addChangeListener(mRealmChangeListener);
                break;
        }
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        getAllFlashcardBoxes();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ListBoxesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onBoxSelected(JpnBox box) {
        mBoxesViewModel.onOpenLearningJpnFragment(this, box);
    }

    @Override
    public void onBoxSelected(VieBox box) {
    }

    @Override
    public void onBoxSelected(KanjiBox box) {
    }

    @Override
    public void onBoxSelected(GrammarBox box) {
    }

    /**
     * Parcelable
     */
    protected ListBoxesViewModel(Parcel in) {
        mBoxType = in.readInt();
    }

    public static final Creator<ListBoxesViewModel> CREATOR = new Creator<ListBoxesViewModel>() {
        @Override
        public ListBoxesViewModel createFromParcel(Parcel in) {
            return new ListBoxesViewModel(in);
        }

        @Override
        public ListBoxesViewModel[] newArray(int size) {
            return new ListBoxesViewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mBoxType);
    }
}
