package com.laptevn.interpreter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuestionInterpreterTest {
    private final static String ERROR_NOT_INTERPRETED = "Isn't interpreted";
    private final static String ERROR_WRONG_ANSWERS_COUNT = "Should be only 1 answer";

    private final InterpreterContextFactory contextFactory = new InterpreterContextFactory();

    @Test
    public void emptyText() throws InterpreterException {
        assertFalse(createInterpreter().interpret("", contextFactory.create()));
    }

    @Test(expected = NullPointerException.class)
    public void nullText() throws InterpreterException {
        assertFalse(createInterpreter().interpret(null, contextFactory.create()));
    }

    @Test
    public void notQuestion() throws InterpreterException {
        assertFalse(createInterpreter().interpret("how much is pish tegj glob glob ", contextFactory.create()));
    }

    @Test
    public void noExpression() throws InterpreterException {
        assertFalse(createInterpreter().interpret("how much pish tegj glob glob ?", contextFactory.create()));
    }

    @Test
    public void noRightPart() throws InterpreterException {
        assertFalse(createInterpreter().interpret("how much is  ?", contextFactory.create()));
    }

    @Test(expected = InterpreterException.class)
    public void unknownSymbols() throws InterpreterException {
        createInterpreter().interpret("how much is pish tegj glob glob ?", contextFactory.create());
    }

    @Test
    public void manySymbols() throws InterpreterException {
        InterpreterContext context = contextFactory.create();
        context.getItemToNumeralTranslation().put("glob", "I");
        context.getItemToNumeralTranslation().put("prok", "V");
        context.getItemToNumeralTranslation().put("pish", "X");
        context.getItemToNumeralTranslation().put("tegj", "L");
        assertTrue(ERROR_NOT_INTERPRETED, createInterpreter().interpret("how much is pish tegj glob glob ?", context));

        assertEquals(ERROR_WRONG_ANSWERS_COUNT, 1, context.getAnswers().size());
        assertEquals("pish tegj glob glob is 42", context.getAnswers().get(context.getAnswers().size() - 1));
    }

    @Test
    public void oneSymbols() throws InterpreterException {
        InterpreterContext context = contextFactory.create();
        context.getItemToNumeralTranslation().put("glob", "I");
        assertTrue(ERROR_NOT_INTERPRETED, createInterpreter().interpret("is glob ?", context));

        assertEquals(ERROR_WRONG_ANSWERS_COUNT, 1, context.getAnswers().size());
        assertEquals("glob is 1", context.getAnswers().get(context.getAnswers().size() - 1));
    }

    @Test(expected = InterpreterException.class)
    public void noNumericButWithQuality() throws InterpreterException {
        InterpreterContext context = contextFactory.create();
        context.getQualityFactors().put("Gold", 0.5f);
        createInterpreter().interpret("how much is Gold  ?", context);
    }

    @Test(expected = InterpreterException.class)
    public void unknownQuality() throws InterpreterException {
        InterpreterContext context = contextFactory.create();
        context.getItemToNumeralTranslation().put("glob", "I");
        createInterpreter().interpret("how much is glob Silver ?", context);
    }

    @Test
    public void knownQuality() throws InterpreterException {
        InterpreterContext context = contextFactory.create();
        context.getItemToNumeralTranslation().put("glob", "I");
        context.getItemToNumeralTranslation().put("prok", "V");
        context.getItemToNumeralTranslation().put("pish", "X");
        context.getItemToNumeralTranslation().put("tegj", "L");

        context.getQualityFactors().put("Gold", 0.5f);
        assertTrue(ERROR_NOT_INTERPRETED, createInterpreter().interpret("how much is pish tegj glob glob Gold  ?", context));

        assertEquals(ERROR_WRONG_ANSWERS_COUNT, 1, context.getAnswers().size());
        assertEquals("pish tegj glob glob Gold is 21", context.getAnswers().get(context.getAnswers().size() - 1));
    }

    @Test
    public void withCredits() throws InterpreterException {
        InterpreterContext context = contextFactory.create();
        context.getItemToNumeralTranslation().put("glob", "V");
        context.getQualityFactors().put("Iron", 0.5f);
        assertTrue(ERROR_NOT_INTERPRETED, createInterpreter().interpret("how many Credits is glob Iron ?", context));

        assertEquals(ERROR_WRONG_ANSWERS_COUNT, 1, context.getAnswers().size());
        assertEquals("glob Iron is 2 Credits", context.getAnswers().get(context.getAnswers().size() - 1));
    }

    private static QuestionInterpreter createInterpreter() {
        return new QuestionInterpreter(new NumeralExtractor());
    }
}