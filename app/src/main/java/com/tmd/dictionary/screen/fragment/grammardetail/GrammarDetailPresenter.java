package com.tmd.dictionary.screen.fragment.grammardetail;

/**
 * Listens to user actions from the UI ({@link GrammarDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
final class GrammarDetailPresenter implements GrammarDetailContract.Presenter {
    private static final String TAG = GrammarDetailPresenter.class.getName();
    private final GrammarDetailContract.ViewModel mViewModel;

    public GrammarDetailPresenter(GrammarDetailContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
