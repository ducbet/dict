package com.tmd.dictionary.screen.fragment.VieJav;

/**
 * Listens to user actions from the UI ({@link VieJavFragment}), retrieves the data and updates
 * the UI as required.
 */
final class VieJavPresenter implements VieJavContract.Presenter {
    private static final String TAG = VieJavPresenter.class.getName();
    private final VieJavContract.ViewModel mViewModel;

    public VieJavPresenter(VieJavContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void search(String needSearch) {
    }
}
