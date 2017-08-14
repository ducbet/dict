package com.tmd.dictionary.screen.fragment.JavVie;

/**
 * Listens to user actions from the UI ({@link JavVieFragment}), retrieves the data and updates
 * the UI as required.
 */
final class JavViePresenter implements JavVieContract.Presenter {
    private static final String TAG = JavViePresenter.class.getName();
    private final JavVieContract.ViewModel mViewModel;

    public JavViePresenter(JavVieContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
