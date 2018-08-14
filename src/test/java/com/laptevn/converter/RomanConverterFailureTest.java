package com.laptevn.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RomanConverterFailureTest {
    private final String romanNumeral;

    public RomanConverterFailureTest(String romanNumeral) {
        this.romanNumeral = romanNumeral;
    }

    @Parameterized.Parameters(name = "{0} Roman to Arabic")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "P" },
                { "IXL" },
                { "IIII" },
                { "IM" },
                { "XM" },
                { "CCM" },
                { "VX" },
                { "LC" },
                { "DM" }
        });
    }

    @Test(expected = InvalidFormatException.class)
    public void verifyConversion() throws InvalidFormatException {
        new RomanConverter().convert(romanNumeral);
    }
}