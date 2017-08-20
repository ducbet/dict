package com.tmd.dictionary.screen.fragment.jpnworddetail;

/**
 * Listens to user actions from the UI ({@link JpnDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
final class JpnDetailPresenter implements JpnWordDetailContract.Presenter {
    private static final String TAG = JpnDetailPresenter.class.getName();
    private final JpnWordDetailContract.ViewModel mViewModel;

    public JpnDetailPresenter(JpnWordDetailContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
