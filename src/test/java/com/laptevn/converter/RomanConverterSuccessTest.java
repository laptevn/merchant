package com.laptevn.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RomanConverterSuccessTest {
    private final String romanNumeral;
    private final int arabicNumeral;

    public RomanConverterSuccessTest(String romanNumeral, int arabicNumeral) {
        this.romanNumeral = romanNumeral;
        this.arabicNumeral = arabicNumeral;
    }

    @Parameterized.Parameters(name = "{0} Roman is {1} Arabic")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "I", 1 },
                { "V", 5 },
                { "X", 10 },
                { "L", 50 },
                { "C", 100 },
                { "D", 500 },
                { "M", 1000 },
                { "MMVI", 2006 },
                { "MCMXLIV", 1944 },
                { "MCMIII", 1903 },
                { "XXXIX", 39 },
                { "CC", 200 },
                { "MMM", 200 },
                { "DXC", 590 },
                { "CD", 400 }
        });
    }

    @Test
    public void verifyConversion() throws InvalidFormatException {
        assertEquals(arabicNumeral, new RomanConverter().convert(romanNumeral));
    }
}