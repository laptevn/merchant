package com.laptevn.converter;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RomanSymbolVerificationTest {
    @Test
    public void romanSymbol() {
        assertTrue(new RomanSymbolConverter().isValidRomanSymbol('M'));
    }

    @Test
    public void notRomanSymbol() {
        assertFalse(new RomanSymbolConverter().isValidRomanSymbol('O'));
    }
}