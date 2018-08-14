package com.laptevn.converter;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Checks if a subtraction of digits is correct for Roman numeric system.
 */
@ThreadSafe
public class SubtractionValidator {
    private final Map<Integer, Integer> allowedCurrentToPrevious;

    public SubtractionValidator() {
        allowedCurrentToPrevious = new HashMap<>();
        allowedCurrentToPrevious.put(5, 1);
        allowedCurrentToPrevious.put(10, 1);
        allowedCurrentToPrevious.put(50, 10);
        allowedCurrentToPrevious.put(100, 10);
        allowedCurrentToPrevious.put(500, 100);
        allowedCurrentToPrevious.put(1000, 100);
    }

    boolean isSubtractionValid(int previousDigit, int currentDigit) {
        return allowedCurrentToPrevious.containsKey(currentDigit) && allowedCurrentToPrevious.get(currentDigit).equals(previousDigit);
    }
}