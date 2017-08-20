package com.tmd.dictionary.screen.fragment.kanjidetail;

/**
 * Listens to user actions from the UI ({@link KanjiDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
final class KanjiDetailPresenter implements KanjiDetailContract.Presenter {
    private static final String TAG = KanjiDetailPresenter.class.getName();
    private final KanjiDetailContract.ViewModel mViewModel;

    public KanjiDetailPresenter(KanjiDetailContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
