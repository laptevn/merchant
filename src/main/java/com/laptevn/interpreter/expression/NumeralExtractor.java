package com.laptevn.interpreter.expression;

import com.laptevn.converter.InvalidFormatException;
import net.jcip.annotations.NotThreadSafe;

import java.util.Optional;

/**
 * Extracts numeral value from symbols.
 */
@NotThreadSafe
public class NumeralExtractor {
    Optional<Integer> extract(String[] numeralSymbols, InterpreterContext context) throws InterpreterException {
        StringBuilder numeral = new StringBuilder();
        for (String numeralSymbol : numeralSymbols) {
            String trimmedSymbol = numeralSymbol.trim();
            if (!context.getItemToNumeralTranslation().containsKey(trimmedSymbol)) {
                throw new InterpreterException("Numeral symbol is not defined");
            }

            numeral.append(context.getItemToNumeralTranslation().get(trimmedSymbol));
        }

        try {
            return numeral.length() <= 0
                    ? Optional.empty()
                    : Optional.of(context.getConverter().convert(numeral.toString()));
        } catch (InvalidFormatException e) {
            throw new InterpreterException("Cannot interpret a numeral", e);
        }
    }
}