package com.tmd.dictionary.screen.fragment.listboxes;

import com.tmd.dictionary.data.model.GrammarBox;
import com.tmd.dictionary.data.model.JpnBox;
import com.tmd.dictionary.data.model.KanjiBox;
import com.tmd.dictionary.data.model.VieBox;
import com.tmd.dictionary.data.source.DataSource;
import com.tmd.dictionary.data.source.Repository;

import io.realm.RealmResults;

/**
 * Listens to user actions from the UI ({@link ListBoxesFragment}), retrieves the data and updates
 * the UI as required.
 */
final class ListBoxesPresenter implements ListBoxesContract.Presenter {
    private static final String TAG = ListBoxesPresenter.class.getName();
    private final ListBoxesContract.ViewModel mViewModel;
    private DataSource mRepository;

    public ListBoxesPresenter(ListBoxesContract.ViewModel viewModel, Repository repository) {
        mViewModel = viewModel;
        mRepository = repository;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public RealmResults<JpnBox> getAllJpnBoxes() {
        return mRepository.getAllJpnBoxes();
    }

    @Override
    public RealmResults<VieBox> getAllVieBoxes() {
        return mRepository.getAllVieBoxes();
    }

    @Override
    public RealmResults<KanjiBox> getAllKanjiBoxes() {
        return mRepository.getAllKanjiBoxes();
    }

    @Override
    public RealmResults<GrammarBox> getAllGrammarBoxes() {
        return mRepository.getAllGrammarBoxes();
    }
}
