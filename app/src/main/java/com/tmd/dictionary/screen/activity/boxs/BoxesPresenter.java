package com.tmd.dictionary.screen.activity.boxs;

/**
 * Listens to user actions from the UI ({@link BoxesActivity}), retrieves the data and updates
 * the UI as required.
 */
final class BoxesPresenter implements BoxesContract.Presenter {
    private static final String TAG = BoxesPresenter.class.getName();
    private final BoxesContract.ViewModel mViewModel;

    public BoxesPresenter(BoxesContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
