package com.laptevn.interpreter;

import java.util.Arrays;
import java.util.List;

/**
 * Interprets text and produces output with a result.
 */
public class TextInterpreter {
    private final List<Interpreter> interpreters;
    private final InterpreterContextFactory interpreterContextFactory;

    public TextInterpreter(List<Interpreter> interpreters, InterpreterContextFactory interpreterContextFactory) {
        this.interpreters = interpreters;
        this.interpreterContextFactory = interpreterContextFactory;
    }

    public String[] interpret(String[] lines) {
        InterpreterContext interpreterContext = interpreterContextFactory.create();
        Arrays.stream(lines).forEach(line -> {
            if (!interpreters.stream().anyMatch(interpreter -> {
                try {
                    return interpreter.interpret(line.trim(), interpreterContext);
                } catch (InterpreterException e) {
                    //TODO: Handle exception by writing it to output
                    return true;
                }
            })) {
                //TODO: Handle error here. Either stop further execution or continue it.
            }
        });

        return null;
    }
}