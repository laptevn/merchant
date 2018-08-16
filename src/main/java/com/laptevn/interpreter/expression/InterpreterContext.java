package com.laptevn.interpreter.expression;

import com.laptevn.converter.Converter;
import com.laptevn.converter.RomanSymbolConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterpreterContext {
    private final RomanSymbolConverter romanSymbolConverter;
    private final Converter converter;
    private final Map<String, String> itemToNumeralTranslation = new HashMap<>();
    private final Map<String, Float> qualityFactors = new HashMap<>();
    private final List<String> answers = new ArrayList<>();

    public InterpreterContext(RomanSymbolConverter romanSymbolConverter, Converter converter) {
        this.romanSymbolConverter = romanSymbolConverter;
        this.converter = converter;
    }

    RomanSymbolConverter getRomanSymbolConverter() {
        return romanSymbolConverter;
    }

    Converter getConverter() {
        return converter;
    }

    Map<String, String> getItemToNumeralTranslation() {
        return itemToNumeralTranslation;
    }

    Map<String, Float> getQualityFactors() {
        return qualityFactors;
    }

    public List<String> getAnswers() {
        return answers;
    }
}