package com.laptevn.converter;

import net.jcip.annotations.NotThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Checks if a repetition of symbols is correct.
 */
@NotThreadSafe
public class RepetitionValidator {
    private final Map<Character, Integer> symbolsRepetitions;
    private char symbol;
    private int counter;

    public RepetitionValidator() {
        symbolsRepetitions = new HashMap<>();
        symbolsRepetitions.put('I', 3);
        symbolsRepetitions.put('V', 1);
        symbolsRepetitions.put('X', 3);
        symbolsRepetitions.put('L', 1);
        symbolsRepetitions.put('C', 3);
        symbolsRepetitions.put('D', 1);
        symbolsRepetitions.put('M', 3);
    }

    void addSymbol(char symbol) {
        if (this.symbol == symbol) {
            counter++;
        } else {
            this.symbol = symbol;
            counter = 1;
        }
    }

    boolean isValid() {
        return symbolsRepetitions.containsKey(symbol) && counter <= symbolsRepetitions.get(symbol);
    }
}