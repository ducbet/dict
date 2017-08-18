package com.tmd.dictionary.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by tmd on 16/08/2017.
 */
public class JpnVieContract {
    public class MainContent implements BaseColumns {
        public static final String TABLE_NAME = "fts_main_content";
        public static final String COLUMN_DOC_ID = "docid";
        public static final String COLUMN_ORIGIN = "c0origin";
        public static final String COLUMN_KANA = "c1kana";
        public static final String COLUMN_DEFINITION = "c2definition";
        public static final String COLUMN_PRIORITY = "c3priority";
    }

    public class RelateWord implements BaseColumns {
        public static final String TABLE_NAME = "relate_word";
        public static final String COLUMN_KANJI_ID = "kanji_id";
        public static final String COLUMN_WORD_ID = "word_id";
        public static final String COLUMN_ID = "id";
    }

    public class WordEx implements BaseColumns {
        public static final String TABLE_NAME = "word_ex";
        public static final String COLUMN_WORD_ID = "word_id";
        public static final String COLUMN_EX_ID = "ex_id";
    }

    public class Examples implements BaseColumns {
        public static final String TABLE_NAME = "examples";
        public static final String COLUMN_EXAMPLE = "example";
    }
}
