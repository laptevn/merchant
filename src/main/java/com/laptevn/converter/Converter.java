package com.laptevn.converter;

/**
 * Converts a numeral from string representation to integer using some algorithm.
 */
public interface Converter {
    /**
     * @throws InvalidFormatException if the format of numeral is not correct.
     */
    int convert(String numeral) throws InvalidFormatException;
}