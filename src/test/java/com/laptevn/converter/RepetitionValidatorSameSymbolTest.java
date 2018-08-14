package com.laptevn.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(Parameterized.class)
public class RepetitionValidatorSameSymbolTest {
    private final char symbol;
    private final int repeatsCount;
    private final boolean isRepetitionValid;

    public RepetitionValidatorSameSymbolTest(char symbol, int repeatsCount, boolean isRepetitionValid) {
        this.symbol = symbol;
        this.repeatsCount = repeatsCount;
        this.isRepetitionValid = isRepetitionValid;
    }

    @Parameterized.Parameters(name = "{0} symbol is repeated {1} times. This is valid - {2}")
    public static Collection<Object[]> initializeParameters() {
        return Arrays.asList(new Object[][] {
                { 'I', 3, true },
                { 'I', 4, false },
                { 'X', 3, true },
                { 'X', 4, false },
                { 'C', 3, true },
                { 'C', 4, false },
                { 'M', 3, true },
                { 'M', 4, false },
                { 'V', 1, true },
                { 'V', 2, false },
                { 'L', 1, true },
                { 'L', 2, false },
                { 'D', 1, true },
                { 'D', 2, false }
        });
    }

    @Test
    public void verifyRepetition() {
        RepetitionValidator repetitionValidator = new RepetitionValidator();
        assertFalse("Invalid initial state of validator", repetitionValidator.isValid());

        for (int i = 0; i < repeatsCount; ++i) {
            repetitionValidator.addSymbol(symbol);
        }

        assertEquals("Repetition state is not correct", isRepetitionValid, repetitionValidator.isValid());
    }
}