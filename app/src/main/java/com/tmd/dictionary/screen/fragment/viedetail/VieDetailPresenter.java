package com.tmd.dictionary.screen.fragment.viedetail;

/**
 * Listens to user actions from the UI ({@link VieDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
final class VieDetailPresenter implements VieDetailContract.Presenter {
    private static final String TAG = VieDetailPresenter.class.getName();
    private final VieDetailContract.ViewModel mViewModel;

    public VieDetailPresenter(VieDetailContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
