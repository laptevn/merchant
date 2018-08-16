package com.laptevn.interpreter;

import com.laptevn.interpreter.expression.AssignmentInterpreter;
import com.laptevn.interpreter.expression.Interpreter;
import com.laptevn.interpreter.expression.InterpreterContextFactory;
import com.laptevn.interpreter.expression.NumeralExtractor;
import com.laptevn.interpreter.expression.QualityInterpreter;
import com.laptevn.interpreter.expression.QuestionInterpreter;

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