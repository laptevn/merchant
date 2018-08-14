package com.laptevn.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SubtractionValidatorTest {
    private final int previousDigit;
    private final int currentDigit;
    private final boolean isSubtractionValid;

    public SubtractionValidatorTest(int previousDigit, int currentDigit, boolean isSubtractionValid) {
        this.previousDigit = previousDigit;
        this.currentDigit = currentDigit;
        this.isSubtractionValid = isSubtractionValid;
    }

    @Parameterized.Parameters(name = "{0} can be before {1} = {2}")
    public static Collection<Object[]> initializeParameters() {
        return Arrays.asList(new Object[][] {
                { 1, 5, true },
                { 1, 10, true },
                { 1, 500, false },
                { 10, 50, true },
                { 10, 100, true },
                { 10, 500, false },
                { 100, 500, true },
                { 100, 1000, true },
                { 5, 100, false },
                { 50, 100, false },
                { 500, 1000, false },
                { 10, 1, false }
        });
    }

    @Test
    public void isSubtractValid() {
        assertEquals(isSubtractionValid, new SubtractionValidator().isSubtractionValid(previousDigit, currentDigit));
    }
}