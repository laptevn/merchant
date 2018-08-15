package com.laptevn.interpreter;

import com.laptevn.converter.RomanSymbolConverter;

import java.util.HashMap;
import java.util.Map;

class InterpreterContext {
    private final RomanSymbolConverter romanSymbolConverter;
    private final Map<String, String> itemToNumeralTranslation = new HashMap<>();

    public InterpreterContext(RomanSymbolConverter romanSymbolConverter) {
        this.romanSymbolConverter = romanSymbolConverter;
    }

    public RomanSymbolConverter getRomanSymbolConverter() {
        return romanSymbolConverter;
    }

    public Map<String, String> getItemToNumeralTranslation() { //TODO: Think of hiding Map behind an abstraction
        return itemToNumeralTranslation;
    }
}