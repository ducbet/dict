package com.tmd.dictionary.staticfinal;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tmd on 19/08/2017.
 */
public class ConstantValue {
    public static final String MY_TAG = "MY_TAG";
    public static final String BUNDLE_VIEW_MODEL = "BUNDLE_VIEW_MODEL";
    public static final String BUNDLE_WORD = "BUNDLE_WORD";
    public static Map<String, String> sWordType = new HashMap<>();
    public static final int ITEM_EMPTY_COMPONENT = 1;
    public static final int ITEM_ORIGIN_COMPONENT = 1;
    public static final int ITEM_KANA_COMPONENT = 2;
    public static final int ITEM_KANJIS_COMPONENT = 3;
    public static final int ITEM_EXAMPLES_COMPONENT = 4;
}
