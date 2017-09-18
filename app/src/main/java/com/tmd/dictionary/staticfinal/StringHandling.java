package com.tmd.dictionary.staticfinal;

import java.util.ArrayList;
import java.util.List;

import static com.tmd.dictionary.staticfinal.ConstantValue.JAPANESE_KANJI;
import static com.tmd.dictionary.staticfinal.ConstantValue.JAPANESE_UNICODE;
import static com.tmd.dictionary.staticfinal.ConstantValue.WORD_TYPE;

/**
 * Created by tmd on 18/08/2017.
 */
public class StringHandling {
    public static String format(String input) {
        prepairWordType();
        String sub;
        String replaced;
        int indexOfAsterick;
        int indexOfNewLine;
        while (input.contains("*")) {
            indexOfAsterick = input.indexOf("*", 0);
            indexOfNewLine = input.indexOf("\n", indexOfAsterick);
            sub = input.substring(indexOfAsterick, indexOfNewLine);
            replaced = sub.replace("*", "☆");
            input = input.replace(sub, replaced);
            for (String target : WORD_TYPE.keySet()) {
                if (sub.contains(target)) {
                    replaced = replaced.replace(target, WORD_TYPE.get(target));
                    input = input.replace(sub, replaced);
                }
            }
        }
        return input;
    }

    private static void prepairWordType() {
        if (!WORD_TYPE.isEmpty()) {
            return;
        }
        WORD_TYPE.put("exp", " ");
        WORD_TYPE.put("vs", "    ");
        WORD_TYPE.put("n-t", "danh từ chỉ thời gian");
        WORD_TYPE.put("n-adv", "danh từ làm phó từ");
        WORD_TYPE.put("n-suf", "danh từ hậu tố");
        WORD_TYPE.put("n-vs", "");
        WORD_TYPE.put("n-adj", "danh từ/tính từ");
        WORD_TYPE.put("int", "    ");
        WORD_TYPE.put("v1", "    ");//động từ nhóm 2 (ichidan)
        WORD_TYPE.put("suf", "hậu tố");
        WORD_TYPE.put("pref", "tiền tố");
        WORD_TYPE.put("sl", "    ");// tiếng lóng
        WORD_TYPE.put("num", "số đếm");
        WORD_TYPE.put("adj-i", "tính từ đuôi \"i\"");
        WORD_TYPE.put("adj-na", "tính từ đuôi \"na\"");
        WORD_TYPE.put("adj-pn", "    ");
        WORD_TYPE.put("adj-no", "    ");
        WORD_TYPE.put("adj", "tính từ");
        WORD_TYPE.put("abbr", "viết tắt");
        WORD_TYPE.put("adv", "phó từ");
        WORD_TYPE.put("col", "cách nói thông tục");// cách nói xuồng xã
        WORD_TYPE.put("v5s", "    ");//động từ nhóm 1 (-su)
        WORD_TYPE.put("v5u", "    ");//động từ nhóm 1 (-u)
        WORD_TYPE.put("X", "    ");
        WORD_TYPE.put("iK", "    ");
        WORD_TYPE.put("fem", "    ");
        WORD_TYPE.put("male", "    ");
        WORD_TYPE.put("obs", "    ");
        WORD_TYPE.put("oK", "    ");
        WORD_TYPE.put("uk", "    ");
        WORD_TYPE.put("conj", "    ");
        WORD_TYPE.put("hon", "    ");
        WORD_TYPE.put("pol", "    ");
        WORD_TYPE.put("hum", "    ");
        WORD_TYPE.put("pn", "    ");
        WORD_TYPE.put("prt", "    ");
        WORD_TYPE.put("n", "danh từ");
    }

    public static List<String> japaneseFilter(String string) {
        List<String> result = new ArrayList<>();
        char[] charsArray = string.toCharArray();
        int begin = 0, end;
        boolean beginCheck = false;
        for (int i = 0; i < charsArray.length; i++) {
            if (!beginCheck &&
                JAPANESE_UNICODE.contains(Character.UnicodeBlock.of(charsArray[i]))) {
                begin = i;
                beginCheck = true;
            }
            if (beginCheck &&
                !JAPANESE_UNICODE.contains(Character.UnicodeBlock.of(charsArray[i]))) {
                end = i;
                result.add(string.substring(begin, end));
                beginCheck = false;
            }
        }
        if (beginCheck) {
            result.add(string.substring(begin, charsArray.length));
        }
        return result;
    }

    public static boolean isUnion(String needCheck) {
        boolean hasJapanese = false;
        boolean hasCharNotJapanese = false;
        for (char c : needCheck.toCharArray()) {
            if (JAPANESE_UNICODE.contains(Character.UnicodeBlock.of(c))) {
                hasJapanese = true;
            }
            if (!JAPANESE_UNICODE.contains(Character.UnicodeBlock.of(c))) {
                hasCharNotJapanese = true;
            }
            if (hasCharNotJapanese && hasJapanese) {
                return false;
            }
        }
        return true;
    }

    public static boolean isWordHasKanjis(String needCheck) {
        for (char c : needCheck.toCharArray()) {
            if (JAPANESE_KANJI.contains(Character.UnicodeBlock.of(c))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isjapanese(String needCheck) {
        for (char c : needCheck.toCharArray()) {
            if (!JAPANESE_UNICODE.contains(Character.UnicodeBlock.of(c))) {
                return false;
            }
        }
        return true;
    }
}

