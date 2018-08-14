package com.laptevn.converter;

import net.jcip.annotations.ThreadSafe;

/**
 * Converts Roman numeral to Arabic numeral.
 */
@ThreadSafe
public class RomanConverter implements Converter {
    private final RomanSymbolConverter symbolConverter;
    private final SubtractionValidator subtractionValidator;
    private final RepetitionValidatorFactory repetitionValidatorFactory;

    public RomanConverter(
            RomanSymbolConverter symbolConverter,
            SubtractionValidator subtractionValidator,
            RepetitionValidatorFactory repetitionValidatorFactory) {

        this.symbolConverter = symbolConverter;
        this.subtractionValidator = subtractionValidator;
        this.repetitionValidatorFactory = repetitionValidatorFactory;
    }

    /**
     * @throws InvalidFormatException if the format of Roman numeral is not correct.
     */
    @Override
    public int convert(String romanNumeral) throws InvalidFormatException {
        int result = 0;
        int previousDigit = 0;
        boolean isSubtracted = false;
        RepetitionValidator repetitionValidator = repetitionValidatorFactory.create();

        for (int i = 0; i < romanNumeral.length(); ++i) {
            char currentSymbol = romanNumeral.charAt(i);
            if (!symbolConverter.isValidRomanSymbol(currentSymbol)) {
                throw new InvalidFormatException(String.format("%c is not Roman numeral", currentSymbol));
            }

            repetitionValidator.addSymbol(currentSymbol);
            if (!repetitionValidator.isValid()) {
                throw new InvalidFormatException(String.format("%c is repeated invalid number of times", currentSymbol));
            }

            int currentDigit = symbolConverter.convert(currentSymbol);
            if (i == 0) {
                previousDigit = currentDigit;
                continue;
            }

            boolean needSubtract = previousDigit < currentDigit;
            if (needSubtract) {
                if (isSubtracted) {
                    throw new InvalidFormatException("Cannot subtract already subtracted value");
                }

                if (!subtractionValidator.isSubtractionValid(previousDigit, currentDigit)) {
                    throw new InvalidFormatException("Invalid order in Roman symbols");
                }

                previousDigit = currentDigit - previousDigit;
                isSubtracted = true;

                boolean isIncreasingSequence = i >= 2 && symbolConverter.convert(romanNumeral.charAt(i - 2)) <= previousDigit;
                if (isIncreasingSequence) {
                    throw new InvalidFormatException("A smaller value precedes larger value and cannot be subtracted");
                }
            } else {
                result += previousDigit;
                previousDigit = currentDigit;
                isSubtracted = false;
            }
        }

        result += previousDigit;
        return result;
    }
}