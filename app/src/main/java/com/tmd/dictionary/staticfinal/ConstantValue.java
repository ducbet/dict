package com.tmd.dictionary.staticfinal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by tmd on 19/08/2017.
 */
public class ConstantValue {
    public static final String MY_TAG = "MY_TAG";
    public static final int SCHEMA_VERSION = 1;
    public static final String BUNDLE_VIEW_MODEL = "BUNDLE_VIEW_MODEL";
    public static final String BUNDLE_JPN_WORD = "BUNDLE_JPN_WORD";
    public static final String BUNDLE_VIE_WORD = "BUNDLE_VIE_WORD";
    public static final String BUNDLE_KANJI = "BUNDLE_KANJI";
    public static final String BUNDLE_GRAMMAR = "BUNDLE_GRAMMAR";
    public static final Map<String, String> WORD_TYPE = new HashMap<>();
    public static final String JPN_WORD = "JPN_WORD";
    public static final String VIE_WORD = "VIE_WORD";
    public static final String KANJI = "KANJI";
    public static final String GRAMMAR = "GRAMMAR";
    public static final int DELAY_SEARCH = 100;
    public static final Set<Character.UnicodeBlock> JAPANESE_UNICODE = new HashSet() {
        {
            add(Character.UnicodeBlock.HIRAGANA);
            add(Character.UnicodeBlock.KATAKANA);
            add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);
        }
    };
}
