package com.laptevn.interpreter;

import net.jcip.annotations.ThreadSafe;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Interprets text and produces output with a result.
 */
@ThreadSafe
public class TextInterpreter {
    private static final String ERROR_UNKNOWN_TEXT = "I have no idea what you are talking about";

    private final static Logger logger = Logger.getLogger(TextInterpreter.class);
    private final List<Interpreter> interpreters;
    private final InterpreterContextFactory interpreterContextFactory;

    public TextInterpreter(List<Interpreter> interpreters, InterpreterContextFactory interpreterContextFactory) {
        this.interpreters = interpreters;
        this.interpreterContextFactory = interpreterContextFactory;
    }

    public String[] interpret(String[] lines) {
        Objects.requireNonNull(lines);

        InterpreterContext context = interpreterContextFactory.create();
        Arrays.stream(lines).forEach(line -> {
            if (!interpreters.stream().anyMatch(interpreter -> {
                try {
                    return interpreter.interpret(line.trim(), context);
                } catch (InterpreterException e) {
                    logger.info("Cannot interpret a line", e);
                    context.getAnswers().add(String.format("Error: %s", e.getMessage()));
                    return true;
                }
            })) {
                context.getAnswers().add(ERROR_UNKNOWN_TEXT);
            }
        });

        return context.getAnswers().toArray(new String[context.getAnswers().size()]);
    }
}