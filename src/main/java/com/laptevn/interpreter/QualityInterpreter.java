package com.laptevn.interpreter; //TODO: Think about moving all interpreters to a separate package

import com.laptevn.converter.InvalidFormatException;

import java.util.Arrays;
import java.util.Optional;

/**
 * Interprets quality items like Gold in "glob prok Gold is 57800 Credits".
 */
public class QualityInterpreter implements Interpreter {
    @Override
    public boolean interpret(String text, InterpreterContext context) throws InterpreterException {
        String[] parts = Constants.EXPRESSIONS_SEPARATOR_PATTERN.split(text);
        if (parts.length != Constants.EXPRESSION_PARTS_COUNT) {
            return false;
        }

        if (parts[0].isEmpty() || parts[1].isEmpty()) {
            return false;
        }

        String[] rightParts = Constants.WORDS_SEPARATOR_PATTERN.split(parts[1].trim());
        Optional<Integer> totalPrice = extractFirstNumber(rightParts);
        if (!totalPrice.isPresent()) {
            return false;
        }

        String[] leftParts = Constants.WORDS_SEPARATOR_PATTERN.split(parts[0].trim());
        Optional<Integer> numeral = extractNumeral(Arrays.copyOfRange(leftParts, 0, leftParts.length - 1), context);
        float factor = numeral.isPresent() ? (float) totalPrice.get() / numeral.get() : (float) totalPrice.get();

        String quality = leftParts[leftParts.length - 1].trim();
        if (context.getItemToNumeralTranslation().containsKey(quality)) {
            throw new InterpreterException("No quality provided");
        }

        context.getQualityFactors().put(quality, factor);
        return true;
    }

    private static Optional<Integer> extractFirstNumber(String[] words) {
        for (String word : words) {
            try {
                return Optional.of(Integer.parseInt(word));
            } catch (NumberFormatException ignore) {
            }
        }

        return Optional.empty();
    }

    private static Optional<Integer> extractNumeral(String[] numeralSymbols, InterpreterContext context) throws InterpreterException {
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