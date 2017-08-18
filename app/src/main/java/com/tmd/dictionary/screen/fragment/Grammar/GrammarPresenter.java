package com.tmd.dictionary.screen.fragment.Grammar;

/**
 * Listens to user actions from the UI ({@link GrammarFragment}), retrieves the data and updates
 * the UI as required.
 */
final class GrammarPresenter implements GrammarContract.Presenter {
    private static final String TAG = GrammarPresenter.class.getName();
    private final GrammarContract.ViewModel mViewModel;

    public GrammarPresenter(GrammarContract.ViewModel viewModel) {
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
