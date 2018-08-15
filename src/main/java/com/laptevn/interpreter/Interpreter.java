package com.laptevn.interpreter;

/**
 * Interprets a text if it can.
 */
public interface Interpreter {
    boolean interpret(String text, InterpreterContext context) throws InterpreterException;
}