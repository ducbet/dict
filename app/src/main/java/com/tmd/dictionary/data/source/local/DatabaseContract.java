package com.tmd.dictionary.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by tmd on 16/08/2017.
 */
public class DatabaseContract {
    public class JpnVieContract {
        public class Main implements BaseColumns {
            public static final String TABLE_NAME = "jpn_vie_main";
            public static final String COLUMN_DOC_ID = "docid";
            public static final String COLUMN_ORIGIN = "c0origin";
            public static final String COLUMN_KANA = "c1kana";
            public static final String COLUMN_DEFINITION = "c2definition";
            public static final String COLUMN_PRIORITY = "c3priority";
        }

        public class RelateKanji implements BaseColumns {
            public static final String TABLE_NAME = "jpn_vie_relate_kanji";
            public static final String COLUMN_KANJI_ID = "kanji_id";
            public static final String COLUMN_WORD_ID = "word_id";
            public static final String COLUMN_ID = "id";
        }

        public class RelateEx implements BaseColumns {
            public static final String TABLE_NAME = "jpn_vie_relate_ex";
            public static final String COLUMN_WORD_ID = "word_id";
            public static final String COLUMN_EX_ID = "ex_id";
        }

        public class Examples implements BaseColumns {
            public static final String TABLE_NAME = "jpn_vie_examples";
            public static final String COLUMN_EXAMPLE = "example";
        }
    }

    public class KanjiContract {
        public class Component implements BaseColumns {
            public static final String TABLE_NAME = "kanji_components";
            public static final String COLUMN_ID = "id";
            public static final String COLUMN_RADICAL = "radical";
            public static final String COLUMN_STROKE_COUNT = "stroke_count";
            public static final String COLUMN_HANVIET = "hanviet";
            public static final String COLUMN_READING = "reading";
        }

        public class Relate implements BaseColumns {
            public static final String TABLE_NAME = "kanji_relate_component";
            public static final String COLUMN_KANJI = "kanji_id";
            public static final String COLUMN_RADICAL_ID = "radical_id";
        }

        public class Main implements BaseColumns {
            public static final String TABLE_NAME = "kanji_main";
            public static final String COLUMN_KANJI = "kanji";
            public static final String COLUMN_HANVIET = "hanviet";
            public static final String COLUMN_RADICAL = "radical";
            public static final String COLUMN_STROKE = "stroke";
            public static final String COLUMN_ONYOMI = "onyomi";
            public static final String COLUMN_LEVEL = "level";
            public static final String COLUMN_KUNYOMI = "kunyomi";
            public static final String COLUMN_MEANING = "meaning";
            public static final String COLUMN_FREQ = "freq";
        }
    }

    public class VieJpnContract {
        public class Examples implements BaseColumns {
            public static final String TABLE_NAME = "vie_jpn_examples";
            public static final String COLUMN_EXAMPLE = "example";
        }

        public class RelateEx implements BaseColumns {
            public static final String TABLE_NAME = "vie_jpn_relate_ex";
            public static final String COLUMN_WORD_ID = "word_id";
            public static final String COLUMN_EX_ID = "ex_id";
        }

        public class Main implements BaseColumns {
            public static final String TABLE_NAME = "vie_jpn_main";
            public static final String COLUMN_ORIGIN = "origin";
            public static final String COLUMN_KANA = "kana";
            public static final String COLUMN_DEFINITION = "definition";
        }
    }

    public class GrammarContract {
        public class Examples implements BaseColumns {
            public static final String TABLE_NAME = "grammar_examples";
            public static final String COLUMN_EXAMPLE = "example";
        }

        public class RelateEx implements BaseColumns {
            public static final String TABLE_NAME = "grammar_relate_ex";
            public static final String COLUMN_WORD_ID = "word_id";
            public static final String COLUMN_EX_ID = "ex_id";
        }

        public class Main implements BaseColumns {
            public static final String TABLE_NAME = "grammar_main";
            public static final String COLUMN_ORIGIN = "origin";
            public static final String COLUMN_KANA = "kana";
            public static final String COLUMN_DEFINITION = "definition";
        }
    }
}
