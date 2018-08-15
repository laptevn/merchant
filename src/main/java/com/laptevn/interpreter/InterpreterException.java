package com.laptevn.interpreter;

/**
 * Contains detailed information about interpretation issue.
 */
class InterpreterException extends Exception {
    private static final long serialVersionUID = -1242828704338494242L;

    public InterpreterException(String message) {
        super(message);
    }
}