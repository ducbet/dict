package com.tmd.dictionary.screen.fragment.jpndetail;

import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;

import java.util.List;

/**
 * Exposes the data to be used in the JpnWordDetail screen.
 */
public class JpnDetailViewModel implements JpnDetailContract.ViewModel {
    private JpnDetailContract.Presenter mPresenter;
    private JpnWord mJpnWord;
    private JpnDetailKanjisAdapter mJpnDetailKanjisAdapter;
    private JpnDetailExamplesAdapter mJpnDetailExamplesAdapter;

    public JpnDetailViewModel(JpnWord jpnWord) {
        mJpnWord = jpnWord;
        mJpnDetailKanjisAdapter = new JpnDetailKanjisAdapter(this);
        mJpnDetailExamplesAdapter = new JpnDetailExamplesAdapter(this);
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
        mPresenter.searchKanjis(mJpnWord);
        mPresenter.searchExamples(mJpnWord);
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
    public void onSearchKanjisSuccess(List<Kanji> kanjis) {
        mJpnWord.setKanjis(kanjis);
        mJpnDetailKanjisAdapter.setSource(mJpnWord.getKanjis());
    }

    @Override
    public void onSearchKanjisFailed() {
        onClearKanjisData();
    }

    @Override
    public void onSearchExamplesSuccess(List<String> examples) {
        mJpnDetailExamplesAdapter.setSource(examples);
    }

    @Override
    public void onSearchExamplesFailed() {
        onClearExamplesData();
    }

    @Override
    public void onClearKanjisData() {
        mJpnDetailKanjisAdapter.clearData();
    }

    @Override
    public void onClearExamplesData() {
        mJpnDetailExamplesAdapter.clearData();
    }
}
