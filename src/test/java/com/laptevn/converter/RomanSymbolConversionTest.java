package com.laptevn.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RomanSymbolConversionTest {
    private final char romanNumeral;
    private final int arabicNumeral;

    public RomanSymbolConversionTest(char romanNumeral, int arabicNumeral) {
        this.romanNumeral = romanNumeral;
        this.arabicNumeral = arabicNumeral;
    }

    @Parameterized.Parameters(name = "{0} Roman is {1} Arabic")
    public static Collection<Object[]> initializeParameters() {
        return Arrays.asList(new Object[][] {
                { 'I', 1 },
                { 'V', 5 },
                { 'X', 10 },
                { 'L', 50 },
                { 'C', 100 },
                { 'D', 500 },
                { 'M', 1000 }
        });
    }

    @Test
    public void verifyConversion() {
        assertEquals(arabicNumeral, new RomanSymbolConverter().convert(romanNumeral));
    }
}