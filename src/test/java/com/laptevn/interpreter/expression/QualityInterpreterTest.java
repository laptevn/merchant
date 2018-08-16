package com.laptevn.interpreter.expression;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class QualityInterpreterTest {
    private final InterpreterContextFactory interpreterContextFactory = new InterpreterContextFactory();

    @Test
    public void emptyText() throws InterpreterException {
        assertFalse(createInterpreter().interpret("", interpreterContextFactory.create()));
    }

    @Test(expected = NullPointerException.class)
    public void nullText() throws InterpreterException {
        assertFalse(createInterpreter().interpret(null, interpreterContextFactory.create()));
    }

    @Test
    public void wrongNumberOfParts() throws InterpreterException {
        assertFalse(createInterpreter().interpret("ab cd", interpreterContextFactory.create()));
    }

    @Test
    public void noLeftPart() throws InterpreterException {
        assertFalse(createInterpreter().interpret("is 34 Credits", interpreterContextFactory.create()));
    }

    @Test
    public void noRightPart() throws InterpreterException {
        assertFalse(createInterpreter().interpret("glob Silver is", interpreterContextFactory.create()));
    }

    @Test
    public void noNumberInRightPart() throws InterpreterException {
        assertFalse(createInterpreter().interpret("glob Silver is Credits", interpreterContextFactory.create()));
    }

    @Test(expected = InterpreterException.class)
    public void tooManyUnknowns() throws InterpreterException {
        createInterpreter().interpret("glob Silver is 34 Credits", interpreterContextFactory.create());
    }

    @Test
    public void oneNumeralSymbol() throws InterpreterException {
        InterpreterContext context = interpreterContextFactory.create();
        context.getItemToNumeralTranslation().put("glob", "I");
        createInterpreter().interpret("glob glob Silver is 33 Credits", context);
        assertEquals(16.5f, context.getQualityFactors().get("Silver"), 0.1f);
    }

    @Test
    public void manyNumeralSymbols() throws InterpreterException {
        InterpreterContext context = interpreterContextFactory.create();
        context.getItemToNumeralTranslation().put("glob", "I");
        context.getItemToNumeralTranslation().put("prok", "V");
        createInterpreter().interpret("glob prok Gold is 36 Credits", context);
        assertEquals(9f, context.getQualityFactors().get("Gold"), 0.1f);
    }

    @Test
    public void noNumeralSymbols() throws InterpreterException {
        InterpreterContext context = interpreterContextFactory.create();
        createInterpreter().interpret("Gold is 36", context);
        assertEquals(36f, context.getQualityFactors().get("Gold"), 0.1f);
    }

    @Test(expected = InterpreterException.class)
    public void invalidRomanFormat() throws InterpreterException {
        InterpreterContext context = interpreterContextFactory.create();
        context.getItemToNumeralTranslation().put("glob", "I");
        context.getItemToNumeralTranslation().put("prok", "V");
        createInterpreter().interpret("glob glob prok Gold is 36 Credits", context);
    }

    @Test(expected = InterpreterException.class)
    public void noQuality() throws InterpreterException {
        InterpreterContext context = interpreterContextFactory.create();
        context.getItemToNumeralTranslation().put("glob", "I");
        context.getItemToNumeralTranslation().put("prok", "V");
        createInterpreter().interpret("glob prok is 36 Credits", context);
    }

    private static QualityInterpreter createInterpreter() {
        return new QualityInterpreter(new NumeralExtractor());
    }
}