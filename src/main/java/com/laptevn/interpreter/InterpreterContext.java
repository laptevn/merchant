package com.laptevn.interpreter;

import com.laptevn.converter.Converter;
import com.laptevn.converter.RomanSymbolConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class InterpreterContext {
    private final RomanSymbolConverter romanSymbolConverter;
    private final Converter converter;
    private final Map<String, String> itemToNumeralTranslation = new HashMap<>();
    private final Map<String, Float> qualityFactors = new HashMap<>();
    private final List<String> answers = new ArrayList<>();

    public InterpreterContext(RomanSymbolConverter romanSymbolConverter, Converter converter) {
        this.romanSymbolConverter = romanSymbolConverter;
        this.converter = converter;
    }

    public RomanSymbolConverter getRomanSymbolConverter() {
        return romanSymbolConverter;
    }

    public Converter getConverter() {
        return converter;
    }

    public Map<String, String> getItemToNumeralTranslation() { //TODO: Think of hiding Map behind an abstraction
        return itemToNumeralTranslation;
    }

    public Map<String, Float> getQualityFactors() {
        return qualityFactors;
    }

    public List<String> getAnswers() {
        return answers;
    }
}