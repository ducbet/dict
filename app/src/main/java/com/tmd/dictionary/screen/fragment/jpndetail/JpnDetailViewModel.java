package com.tmd.dictionary.screen.fragment.jpndetail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tmd.dictionary.BR;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.screen.activity.main.MainContract;

/**
 * Exposes the data to be used in the JpnWordDetail screen.
 */
public class JpnDetailViewModel extends BaseObservable implements JpnDetailContract.ViewModel {
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
        mPresenter.isLiked(mJpnWord.getOrigin());
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
    public void onClickKanji(Kanji kanji) {
        mMainViewModel.onOpenKanjiDetailFragment(kanji);
    }

    @Override
    public void onChangeLikeState() {
        mPresenter.changeLikeState(mJpnWord.getOrigin());
    }

    @Override
    public void onSetLiked(Boolean isLiked) {
        mIsLiked = isLiked;
        notifyPropertyChanged(BR.liked);
    }
}
