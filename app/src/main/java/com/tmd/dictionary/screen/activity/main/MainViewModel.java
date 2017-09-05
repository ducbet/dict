package com.tmd.dictionary.screen.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;

import com.tmd.dictionary.R;
import com.tmd.dictionary.data.model.Grammar;
import com.tmd.dictionary.data.model.JpnWord;
import com.tmd.dictionary.data.model.Kanji;
import com.tmd.dictionary.data.model.VieWord;
import com.tmd.dictionary.screen.activity.boxs.BoxesActivity;
import com.tmd.dictionary.screen.activity.history.HistoryActivity;
import com.tmd.dictionary.screen.fragment.grammardetail.GrammarDetailFragment;
import com.tmd.dictionary.screen.fragment.jpndetail.JpnDetailFragment;
import com.tmd.dictionary.screen.fragment.kanjidetail.KanjiDetailFragment;
import com.tmd.dictionary.screen.fragment.search.SearchFragment;
import com.tmd.dictionary.screen.fragment.viedetail.VieDetailFragment;
import com.tmd.dictionary.staticfinal.OpenFragment;
import com.tmd.dictionary.util.DictApplication;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.tmd.dictionary.staticfinal.ConstantValue.SCHEMA_VERSION;

/**
 * Exposes the data to be used in the Main screen.
 */
public class MainViewModel implements MainContract.ViewModel, Parcelable,
    NavigationView.OnNavigationItemSelectedListener {
    private MainContract.Presenter mPresenter;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Realm mRealm;

    public MainViewModel(Context context) {
        mContext = context;
        mFragmentManager = ((MainActivity) mContext).getSupportFragmentManager();
        initRealm();
        initSearchFragment();
    }

    private void initRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder()
            .schemaVersion(SCHEMA_VERSION)
            .assetFile(DictApplication.getContext().getString(R.string.database_name))
            .build();
        mRealm = Realm.getInstance(config);
    }

    private void initSearchFragment() {
        OpenFragment
            .openFragment(mFragmentManager, R.id.frame_layout, SearchFragment.newInstance(this));
    }

    public Context getContext() {
        return mContext;
    }

    public void setNavigationView(DrawerLayout drawerLayout, NavigationView navigationView) {
        mDrawerLayout = drawerLayout;
        mNavigationView = navigationView;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        mRealm.close();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isSearchFragVisibility() {
        return mFragmentManager.getBackStackEntryCount() == 1;
    }

    @Override
    public void onOpenKanjiDetailFragment(Kanji kanji) {
        OpenFragment.openFragment(mFragmentManager, R.id.frame_layout,
            KanjiDetailFragment.newInstance(this, kanji));
    }

    @Override
    public void onOpenJpnWordDetailFragment(JpnWord jpnWord) {
        OpenFragment.openFragment(mFragmentManager, R.id.frame_layout,
            JpnDetailFragment.newInstance(this, jpnWord));
    }

    @Override
    public void onOpenVieWordDetailFragment(VieWord vieWord) {
        OpenFragment.openFragment(mFragmentManager, R.id.frame_layout,
            VieDetailFragment.newInstance(this, vieWord));
    }

    @Override
    public void onOpenGrammarDetailFragment(Grammar grammar) {
        OpenFragment.openFragment(mFragmentManager, R.id.frame_layout,
            GrammarDetailFragment.newInstance(this, grammar));
    }

    @Override
    public void onOpenHistoryActivity() {
        Intent intent = new Intent(mContext, HistoryActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void onOpenBoxesActivity() {
        Intent intent = new Intent(mContext, BoxesActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    /**
     * Parcelable
     */
    protected MainViewModel(Parcel in) {
    }

    public static final Creator<MainViewModel> CREATOR = new Creator<MainViewModel>() {
        @Override
        public MainViewModel createFromParcel(Parcel in) {
            return new MainViewModel(in);
        }

        @Override
        public MainViewModel[] newArray(int size) {
            return new MainViewModel[size];
        }
    };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.history:
                onOpenHistoryActivity();
                break;
            case R.id.flash_card:
                onOpenBoxesActivity();
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawer(Gravity.START);
        return false;
    }
}
