package com.laptevn.interpreter;

import com.laptevn.converter.RepetitionValidatorFactory;
import com.laptevn.converter.RomanConverter;
import com.laptevn.converter.RomanSymbolConverter;
import com.laptevn.converter.SubtractionValidator;

/**
 * Creates an instance of {@link InterpreterContext InterpreterContext} class.
 */
class InterpreterContextFactory {
    public InterpreterContext create() {
        RomanSymbolConverter romanSymbolConverter = new RomanSymbolConverter();
        return new InterpreterContext(
                romanSymbolConverter,
                new RomanConverter(romanSymbolConverter, new SubtractionValidator(), new RepetitionValidatorFactory()));
    }
}