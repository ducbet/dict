package com.tmd.dictionary.screen;

/**
 * BaseView
 */
public interface BaseViewModel<T extends BasePresenter> {

    void onStart();

    void onStop();

    void setPresenter(T presenter);

}
