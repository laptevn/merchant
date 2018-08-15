package com.laptevn.interpreter;

import net.jcip.annotations.NotThreadSafe;

import java.util.Optional;

/**
 * Interprets assignments like "prok is V".
 */
@NotThreadSafe
public class AssignmentInterpreter implements Interpreter {
    /**
     * @throws InterpreterException if there is an issue with assignment interpretation.
     */
    @Override
    public boolean interpret(String text, InterpreterContext context) throws InterpreterException {
        String[] parts = Constants.EXPRESSIONS_SEPARATOR_PATTERN.split(text);
        if (parts.length != 2) {
            return false;
        }

        Optional<String> leftToken = extractToken(parts[0].trim());
        if (!leftToken.isPresent()) {
            return false;
        }

        Optional<String> rightToken = extractToken(parts[1].trim());
        if (!rightToken.isPresent()) {
            return false;
        }

        String romanNumeral = rightToken.get();
        if (romanNumeral.length() != 1 || !context.getRomanSymbolConverter().isValidRomanSymbol(romanNumeral.charAt(0))) {
            throw new InterpreterException("Value can be only Roman numeral symbol");
        }

        context.getItemToNumeralTranslation().put(leftToken.get(), romanNumeral);
        return true;
    }

    private static Optional<String> extractToken(String text) {
        if (text.isEmpty()) {
            return Optional.empty();
        }

        String[] parts = Constants.WORDS_SEPARATOR_PATTERN.split(text);
        return parts.length == 1 ? Optional.of(parts[0].trim()) : Optional.empty();
    }
}