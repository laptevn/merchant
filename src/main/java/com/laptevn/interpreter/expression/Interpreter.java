package com.laptevn.interpreter.expression;

/**
 * Interprets a text if it can.
 */
public interface Interpreter {
    boolean interpret(String text, InterpreterContext context) throws InterpreterException;
}