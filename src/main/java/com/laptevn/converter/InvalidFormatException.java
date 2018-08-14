package com.laptevn.converter;

/**
 * Contains detailed information about an exceptional case.
 */
public class InvalidFormatException extends Exception {
    private static final long serialVersionUID = -6880110722501582691L;

    public InvalidFormatException(String message) {
        super(message);
    }
}