package com.tmd.dictionary.screen.activity.boxs;

import android.content.Context;
import android.os.Parcel;
import android.support.v4.app.FragmentManager;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.JpnBox;
import com.tmd.dictionary.screen.fragment.learningjpn.LearningJpnFragment;
import com.tmd.dictionary.screen.fragment.listboxes.ListBoxesContract;
import com.tmd.dictionary.screen.fragment.listboxes.ListBoxesFragment;
import com.tmd.dictionary.staticfinal.OpenFragment;

import io.realm.Realm;

import static com.tmd.dictionary.staticfinal.ConstantValue.INT_GRAMMAR;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_JPN_WORD;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_KANJI;
import static com.tmd.dictionary.staticfinal.ConstantValue.INT_VIE_WORD;

/**
 * Exposes the data to be used in the FlashCardBoxs screen.
 */
public class BoxesViewModel implements BoxesContract.ViewModel {
    private BoxesContract.Presenter mPresenter;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private Realm mRealm;

    public BoxesViewModel(Context context, Realm realm) {
        mContext = context;
        mRealm = realm;
        mFragmentManager = ((BoxesActivity) mContext).getSupportFragmentManager();
    }

    public Realm getRealm() {
        return mRealm;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(BoxesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onOpenJpnBoxesFragment() {
        OpenFragment.openFragment(mFragmentManager, R.id.frame_layout_boxes,
            ListBoxesFragment.newInstance(this, INT_JPN_WORD));
    }

    @Override
    public void onOpenVieBoxesFragment() {
        OpenFragment.openFragment(mFragmentManager, R.id.frame_layout_boxes,
            ListBoxesFragment.newInstance(this, INT_VIE_WORD));
    }

    @Override
    public void onOpenKanjiBoxesFragment() {
        OpenFragment.openFragment(mFragmentManager, R.id.frame_layout_boxes,
            ListBoxesFragment.newInstance(this, INT_KANJI));
    }

    @Override
    public void onOpenGrammarBoxesFragment() {
        OpenFragment.openFragment(mFragmentManager, R.id.frame_layout_boxes,
            ListBoxesFragment.newInstance(this, INT_GRAMMAR));
    }

    @Override
    public void onOpenLearningJpnFragment(ListBoxesContract.ViewModel mListBoxesViewModel,
                                          JpnBox box) {
        OpenFragment.openFragment(mFragmentManager, R.id.frame_layout_boxes,
            LearningJpnFragment.newInstance(mListBoxesViewModel, box));
    }

    @Override
    public void onOpenLearningVieFragment() {
    }

    @Override
    public void onOpenLearningKanjiFragment() {
    }

    @Override
    public void onOpenLearningGrammarFragment() {
    }

    /**
     * Parcelable
     */
    protected BoxesViewModel(Parcel in) {
    }

    public static final Creator<BoxesViewModel> CREATOR = new Creator<BoxesViewModel>() {
        @Override
        public BoxesViewModel createFromParcel(Parcel in) {
            return new BoxesViewModel(in);
        }

        @Override
        public BoxesViewModel[] newArray(int size) {
            return new BoxesViewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
