package com.tmd.dictionary.screen.fragment.Kanji;

/**
 * Listens to user actions from the UI ({@link KanjiFragment}), retrieves the data and updates
 * the UI as required.
 */
final class KanjiPresenter implements KanjiContract.Presenter {
    private static final String TAG = KanjiPresenter.class.getName();
    private final KanjiContract.ViewModel mViewModel;

    public KanjiPresenter(KanjiContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
