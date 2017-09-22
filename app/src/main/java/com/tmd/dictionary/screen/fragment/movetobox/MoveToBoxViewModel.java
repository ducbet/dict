package com.tmd.dictionary.screen.fragment.movetobox;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.Toast;

import com.tmd.dictionary.BR;
import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.GrammarBox;
import com.tmd.dictionary.data.model.JpnBox;
import com.tmd.dictionary.data.model.KanjiBox;
import com.tmd.dictionary.data.model.VieBox;
import com.tmd.dictionary.screen.OpenableMoveToBoxFrag;
import com.tmd.dictionary.screen.fragment.grammardetail.GrammarDetailViewModel;
import com.tmd.dictionary.screen.fragment.jpndetail.JpnDetailViewModel;
import com.tmd.dictionary.screen.fragment.kanjidetail.KanjiDetailViewModel;
import com.tmd.dictionary.screen.fragment.viedetail.VieDetailViewModel;
import com.tmd.dictionary.staticfinal.SoftKeybroad;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Exposes the data to be used in the MoveToBox screen.
 */
public class MoveToBoxViewModel extends BaseObservable {
    private OpenableMoveToBoxFrag mWordDetailViewModel;
    private Context mContext;
    private BoxAdapter mAdapter;
    private String mWord;
    private int mBoxesCount;
    private RealmChangeListener mRealmChangeListener = new RealmChangeListener<RealmResults>() {
        @Override
        public void onChange(RealmResults boxes) {
            setBoxesCount(boxes.size());
            mAdapter.setSource(boxes);
        }
    };
    private RealmResults mBoxes;

    public MoveToBoxViewModel(OpenableMoveToBoxFrag openableMoveToBoxFrag) {
        mWordDetailViewModel = openableMoveToBoxFrag;
        mContext = mWordDetailViewModel.getContext();
        mWord = mWordDetailViewModel.getWordOrigin();
        mAdapter = new BoxAdapter(this);
        getAllBoxes();
    }

    public void onStop() {
        mBoxes.removeAllChangeListeners();
    }

    public BoxAdapter getAdapter() {
        return mAdapter;
    }

    private void getAllBoxes() {
        mBoxes = mWordDetailViewModel.getAllFlashcardBoxes();
        mBoxes.addChangeListener(mRealmChangeListener);
    }

    public String getWord() {
        return mWord;
    }

    @Bindable
    public String getBoxesCount() {
        return String.valueOf(mBoxesCount);
    }

    public void setBoxesCount(int boxesCount) {
        mBoxesCount = boxesCount;
        notifyPropertyChanged(BR.boxesCount);
    }

    public void createBox() {
        SoftKeybroad.show((Activity) mContext);
        final EditText editText = new EditText(mContext);
        new AlertDialog.Builder(mContext)
            .setTitle("New Box")
            .setView(editText)
            .setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!editText.getText().toString().isEmpty()) {
                        mWordDetailViewModel
                            .createBox(editText.getText().toString(), "description");
                    } else {
                        Toast.makeText(mContext, mContext.getString(R.string.name_empty),
                            Toast.LENGTH_LONG).show();
                    }
                    SoftKeybroad.hide((Activity) mContext);
                }
            })
            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    SoftKeybroad.hide((Activity) mContext);
                }
            })
            .show();
    }

    public void onMoveToBox(JpnBox jpnBox) {
        ((JpnDetailViewModel) mWordDetailViewModel).onMoveToBox(jpnBox,
            ((JpnDetailViewModel) mWordDetailViewModel).getJpnWord());
    }

    public void onMoveToBox(VieBox vieBox) {
        ((VieDetailViewModel) mWordDetailViewModel).onMoveToBox(vieBox,
            ((VieDetailViewModel) mWordDetailViewModel).getVieWord());
    }

    public void onMoveToBox(KanjiBox kanjiBox) {
        ((KanjiDetailViewModel) mWordDetailViewModel).onMoveToBox(kanjiBox,
            ((KanjiDetailViewModel) mWordDetailViewModel).getKanji());
    }

    public void onMoveToBox(GrammarBox grammarBox) {
        ((GrammarDetailViewModel) mWordDetailViewModel).onMoveToBox(grammarBox,
            ((GrammarDetailViewModel) mWordDetailViewModel).getGrammar());
    }
}
