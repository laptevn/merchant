package com.laptevn.interpreter; //TODO: Think about moving all interpreters to a separate package

import net.jcip.annotations.NotThreadSafe;

import java.util.Arrays;
import java.util.Optional;

/**
 * Interprets quality items like Gold in "glob prok Gold is 57800 Credits".
 */
@NotThreadSafe
public class QualityInterpreter implements Interpreter {
    private final NumeralExtractor numeralExtractor;

    public QualityInterpreter(NumeralExtractor numeralExtractor) {
        this.numeralExtractor = numeralExtractor;
    }

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
        Optional<Integer> numeral = numeralExtractor.extract(Arrays.copyOfRange(leftParts, 0, leftParts.length - 1), context);
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
}