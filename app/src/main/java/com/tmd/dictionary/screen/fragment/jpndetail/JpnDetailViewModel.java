package com.tmd.dictionary.screen.fragment.jpndetail;

import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.screen.activity.main.MainContract;

/**
 * Exposes the data to be used in the JpnWordDetail screen.
 */
public class JpnDetailViewModel implements JpnDetailContract.ViewModel {
    private MainContract.ViewModel mMainViewModel;
    private JpnDetailContract.Presenter mPresenter;
    private JpnWord mJpnWord;
    private JpnDetailKanjisAdapter mJpnDetailKanjisAdapter;
    private JpnDetailExamplesAdapter mJpnDetailExamplesAdapter;

    public JpnDetailViewModel(MainContract.ViewModel mainViewModel, JpnWord jpnWord) {
        mMainViewModel = mainViewModel;
        mJpnWord = jpnWord;
        mJpnDetailKanjisAdapter = new JpnDetailKanjisAdapter(this, jpnWord.getKanjis());
        mJpnDetailExamplesAdapter = new JpnDetailExamplesAdapter(this, jpnWord.getExamples());
    }

    public JpnWord getJpnWord() {
        return mJpnWord;
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
}
