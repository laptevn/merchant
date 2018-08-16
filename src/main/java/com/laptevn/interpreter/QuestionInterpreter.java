package com.laptevn.interpreter;

import net.jcip.annotations.NotThreadSafe;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Interprets questions items like "how much is pish tegj glob glob ?".
 */
@NotThreadSafe
public class QuestionInterpreter implements Interpreter {
    private static final char QUESTION_MARKER = '?';
    private static final String UNIT_NAME = "Credits";
    private static final Pattern UNIT_NAME_PATTERN = Pattern.compile("(^|\\W)" + UNIT_NAME +"(\\W|$)");
    private final NumeralExtractor numeralExtractor;

    public QuestionInterpreter(NumeralExtractor numeralExtractor) {
        this.numeralExtractor = numeralExtractor;
    }

    @Override
    public boolean interpret(String text, InterpreterContext context) throws InterpreterException {
        if (text.isEmpty() || text.charAt(text.length() - 1) != QUESTION_MARKER) {
            return false;
        }

        String[] parts = Constants.EXPRESSIONS_SEPARATOR_PATTERN.split(text.substring(0, text.length() - 1));
        if (parts.length != Constants.EXPRESSION_PARTS_COUNT) {
            return false;
        }

        String rightPart = parts[1].trim();
        if (rightPart.isEmpty()) {
            return false;
        }

        String[] rightParts = Constants.WORDS_SEPARATOR_PATTERN.split(rightPart);
        int result = calculateResult(rightParts, context);
        String unit = UNIT_NAME_PATTERN.matcher(parts[0]).find() ? " " + UNIT_NAME : "";
        context.getAnswers().add(String.format("%s is %d%s", rightPart, result, unit));
        return true;
    }

    private int calculateResult(String[] rightParts, InterpreterContext context) throws InterpreterException {
        String qualitySymbol = rightParts[rightParts.length - 1];
        Optional<Float> qualityFactor = context.getQualityFactors().containsKey(qualitySymbol)
                ? Optional.of(context.getQualityFactors().get(qualitySymbol))
                : Optional.empty();

        String[] numericSymbols = qualityFactor.isPresent() ? Arrays.copyOfRange(rightParts, 0, rightParts.length - 1) : rightParts;
        if (numericSymbols.length <= 0) {
            throw new InterpreterException("No symbols are provided");
        }

        int numeral = numeralExtractor.extract(numericSymbols, context).get();
        return qualityFactor.map(value -> (int) (numeral * value)).orElse(numeral);
    }
}