package com.laptevn.converter;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Converts Roman symbol to Arabic digit.
 */
@ThreadSafe
public class RomanSymbolConverter {
    private final Map<Character, Integer> conversions;

    public RomanSymbolConverter() {
        conversions = new HashMap<>();
        conversions.put('I', 1);
        conversions.put('V', 5);
        conversions.put('X', 10);
        conversions.put('L', 50);
        conversions.put('C', 100);
        conversions.put('D', 500);
        conversions.put('M', 1000);
    }

    int convert(char romanSymbol) {
        return conversions.get(romanSymbol);
    }

    public boolean isValidRomanSymbol(char romanSymbol) {
        return conversions.containsKey(romanSymbol);
    }
}