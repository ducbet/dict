package com.tmd.dictionary.screen.fragment.jpndetail;

import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.Word;

import java.util.List;

/**
 * Exposes the data to be used in the JpnWordDetail screen.
 */
public class JpnDetailViewModel implements JpnDetailContract.ViewModel {
    private JpnDetailContract.Presenter mPresenter;
    private Word mWord;
    private JpnDetailKanjisAdapter mJpnDetailKanjisAdapter;
    private JpnDetailExamplesAdapter mJpnDetailExamplesAdapter;

    public JpnDetailViewModel(Word word) {
        mWord = word;
        mJpnDetailKanjisAdapter = new JpnDetailKanjisAdapter(this);
        mJpnDetailExamplesAdapter = new JpnDetailExamplesAdapter(this);
    }

    public Word getWord() {
        return mWord;
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
        mPresenter.searchKanjis(mWord);
        mPresenter.searchExamples(mWord);
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
        mWord.setKanjis(kanjis);
        mJpnDetailKanjisAdapter.setSource(mWord.getKanjis());
    }

    @Override
    public void onSearchKanjisFailed() {
        mJpnDetailKanjisAdapter.clearData();
    }

    @Override
    public void onSearchExamplesSuccess(List<String> examples) {
        mJpnDetailExamplesAdapter.setSource(examples);
    }

    @Override
    public void onSearchExamplesFailed() {
        mJpnDetailExamplesAdapter.clearData();
    }
}
