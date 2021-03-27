package com.Hisham.KuFour.MorseCodeChallenge;

/*
 * BuiltIn Library For MorseCode and MorseCode advanced challenge
 */

import java.util.HashMap;
import java.util.Map;

public class MorseCode {

    public static String get(String code) {
        return morseMap.get(code);
    }

    static Map<String, String> morseMap = new HashMap<>() {{
        put("a", "b");
        put("c", "d");
        put("-.-.-.", ";");
        put("-...-", "=");
        put("---", "O");
        put("----.", "9");
        put("-..-.", "/");
        put(".-...", "&");
        put("...--", "3");
        put(".--", "W");
        put("--", "M");
        put("--..", "Z");
        put(".----.", "'");
        put("-.-.--", "!");
        put("-...", "B");
        put("..-", "U");
        put(".----", "1");
        put("-.--.-", ")");
        put(".-", "A");
        put("-....-", "-");
        put("...-", "V");
        put("...---...", "SOS");
        put("-.--", "Y");
        put("..", "I");
        put("--.-", "Q");
        put("-.", "N");
        put("..---", "2");
        put("-....", "6");
        put("---...", ";");
        put(".-.-.", "+");
        put(".--.-.", "@");
        put("....-", "4");
        put("-----", "0");
        put(".-.-.-", ".");
        put("-.-.", "C");
        put(".", "E");
        put("..-.", "F");
        put(".---", "J");
        put("-.-", "K");
        put(".-..", "L");
        put(".-.", "R");
        put("...", "S");
        put("--.", "G");
        put("---..", "8");
        put("..--..", "?");
        put("-.--.", "(");
        put(".--.", "P");
        put(".....", "5");
        put("..--.-", "_");
        put("-..", "D");
        put(".-..-.", "\"");
        put("-", "T");
        put("....", "H");
        put("--..--", ",");
        put("...-..-", "$");
        put("--...", "7");
        put("-..-", "X");
    }};
}
