package com.tmd.dictionary.screen.fragment.jpndetail;

import com.tmd.dictionary.data.model.Word;

/**
 * Exposes the data to be used in the JpnWordDetail screen.
 */
public class JpnDetailViewModel implements JpnDetailContract.ViewModel {
    private JpnDetailContract.Presenter mPresenter;
    private Word mWord;

    public JpnDetailViewModel(Word word) {
        mWord = word;
        createComponents();
    }

    private void createComponents() {
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
}
