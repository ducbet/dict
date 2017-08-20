package com.tmd.dictionary.screen.fragment.jpndetail;

import com.tmd.dictionary.data.model.Word;
import com.tmd.dictionary.data.viewmodel.OriginShowViewModel;

/**
 * Exposes the data to be used in the JpnWordDetail screen.
 */
public class JpnDetailViewModel implements JpnDetailContract.ViewModel {
    private JpnDetailContract.Presenter mPresenter;
    private Word mWord;
    private JpnDetailAdapter mAdapter;

    public JpnDetailViewModel(Word word) {
        mAdapter = new JpnDetailAdapter(this);
        mWord = word;
        createComponents();
    }

    private void createComponents() {
        OriginShowViewModel originShowViewModel = new OriginShowViewModel();
        originShowViewModel.setWord(mWord);
        mAdapter.setSource(originShowViewModel);
    }

    public JpnDetailAdapter getAdapter() {
        return mAdapter;
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
