package com.laptevn.interpreter;

import java.util.Arrays;
import java.util.List;

/**
 * Creates an instance of {@link TextInterpreter TextInterpreter} class.
 */
public class TextInterpreterFactory {
    public TextInterpreter create() {
        return new TextInterpreter(createInterpreters(new NumeralExtractor()), new InterpreterContextFactory());
    }

    private List<Interpreter> createInterpreters(NumeralExtractor numeralExtractor) {
        return Arrays.asList(
                new AssignmentInterpreter(),
                new QualityInterpreter(numeralExtractor),
                new QuestionInterpreter(numeralExtractor)
        );
    }
}