package com.tmd.dictionary.data.source;

import com.tmd.dictionary.data.source.local.LocalDataSource;

import java.util.List;

/**
 * Created by tmd on 16/08/2017.
 */
public class Repository implements DataSource {
    private LocalDataSource mLocalDataSource;

    public Repository(LocalDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    public List<String> JpnVieDefinition(String input) {
        return mLocalDataSource.JpnVieDefinition(input);
    }
}
