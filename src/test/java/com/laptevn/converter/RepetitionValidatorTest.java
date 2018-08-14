package com.laptevn.converter;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RepetitionValidatorTest {
    @Test
    public void resetCounter() {
        RepetitionValidator repetitionValidator = new RepetitionValidator();
        repetitionValidator.addSymbol('D');
        assertTrue(repetitionValidator.isValid());

        repetitionValidator.addSymbol('I');
        assertTrue(repetitionValidator.isValid());

        repetitionValidator.addSymbol('D');
        assertTrue(repetitionValidator.isValid());
    }
}