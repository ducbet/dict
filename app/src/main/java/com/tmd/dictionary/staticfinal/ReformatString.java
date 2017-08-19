package com.tmd.dictionary.staticfinal;

import com.tmd.dictionary.data.model.Word;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tmd on 18/08/2017.
 */
public class ReformatString {
    public static Map<String, String> sWordType = new HashMap<>();

    public static void formatWord(Word word) {
        prepairWordType();
        String definition = word.getDefinition();
        String sub;
        String replaced;
        int indexOfAsterick;
        int indexOfNewLine;
        while (definition.contains("*")) {
            indexOfAsterick = definition.indexOf("*", 0);
            indexOfNewLine = definition.indexOf("\n", indexOfAsterick);
            sub = definition.substring(indexOfAsterick, indexOfNewLine);
            replaced = sub.replace("*", "☆");
            for (String target : sWordType.keySet()) {
                if (sub.contains(target)) {
                    replaced = replaced.replace(target, sWordType.get(target));
                    definition = definition.replace(sub, replaced);
                }
            }
        }
        word.setDefinition(definition);
    }

    private static void prepairWordType() {
        if (!sWordType.isEmpty()) {
            return;
        }
        sWordType.put("exp", " ");
        sWordType.put("vs", "    ");
        sWordType.put("n-t", "danh từ chỉ thời gian");
        sWordType.put("n-adv", "danh từ làm phó từ");
        sWordType.put("n-suf", "danh từ hậu tố");
        sWordType.put("n-vs", "");
        sWordType.put("n-adj", "danh từ/tính từ");
        sWordType.put("int", "    ");
        sWordType.put("v1", "    ");//động từ nhóm 2 (ichidan)
        sWordType.put("suf", "hậu tố");
        sWordType.put("pref", "tiền tố");
        sWordType.put("sl", "    ");// tiếng lóng
        sWordType.put("adj-i", "tính từ đuôi \"i\"");
        sWordType.put("adj-na", "tính từ đuôi \"na\"");
        sWordType.put("adj-pn", "    ");
        sWordType.put("adj-no", "    ");
        sWordType.put("adj", "tính từ");
        sWordType.put("abbr", "viết tắt");
        sWordType.put("adv", "phó từ");
        sWordType.put("col", "cách nói thông tục");// cách nói xuồng xã
        sWordType.put("v5s", "    ");//động từ nhóm 1 (-su)
        sWordType.put("v5u", "    ");//động từ nhóm 1 (-u)
        sWordType.put("X", "    ");
        sWordType.put("iK", "    ");
        sWordType.put("fem", "    ");
        sWordType.put("male", "    ");
        sWordType.put("obs", "    ");
        sWordType.put("oK", "    ");
        sWordType.put("uk", "    ");
        sWordType.put("conj", "    ");
        sWordType.put("hon", "    ");
        sWordType.put("pol", "    ");
        sWordType.put("hum", "    ");
        sWordType.put("pn", "    ");
        sWordType.put("prt", "    ");
        sWordType.put("n", "Danh từ");
    }
}

