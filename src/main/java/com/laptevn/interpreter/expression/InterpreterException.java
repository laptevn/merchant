package com.laptevn.interpreter.expression;

/**
 * Contains detailed information about interpretation issue.
 */
public class InterpreterException extends Exception {
    private static final long serialVersionUID = 1776711606998347925L;

    public InterpreterException(String message) {
        super(message);
    }

    public InterpreterException(String message, Throwable cause) {
        super(message, cause);
    }
}