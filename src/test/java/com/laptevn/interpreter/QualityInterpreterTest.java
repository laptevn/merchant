package com.laptevn.interpreter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class QualityInterpreterTest {
    @Test
    public void wrongNumberOfParts() throws InterpreterException {
        assertFalse(new QualityInterpreter().interpret("ab cd", new InterpreterContextFactory().create()));
    }

    @Test
    public void noLeftPart() throws InterpreterException {
        assertFalse(new QualityInterpreter().interpret("is 34 Credits", new InterpreterContextFactory().create()));
    }

    @Test
    public void noRightPart() throws InterpreterException {
        assertFalse(new QualityInterpreter().interpret("glob Silver is", new InterpreterContextFactory().create()));
    }

    @Test
    public void noNumberInRightPart() throws InterpreterException {
        assertFalse(new QualityInterpreter().interpret("glob Silver is Credits", new InterpreterContextFactory().create()));
    }

    @Test(expected = InterpreterException.class)
    public void tooManyUnknowns() throws InterpreterException {
        new QualityInterpreter().interpret("glob Silver is 34 Credits", new InterpreterContextFactory().create());
    }

    @Test
    public void oneNumeralSymbol() throws InterpreterException {
        InterpreterContext context = new InterpreterContextFactory().create();
        context.getItemToNumeralTranslation().put("glob", "I");
        new QualityInterpreter().interpret("glob glob Silver is 33 Credits", context);
        assertEquals(16.5f, context.getQualityFactors().get("Silver"), 0.1f);
    }

    @Test
    public void manyNumeralSymbols() throws InterpreterException {
        InterpreterContext context = new InterpreterContextFactory().create();
        context.getItemToNumeralTranslation().put("glob", "I");
        context.getItemToNumeralTranslation().put("prok", "V");
        new QualityInterpreter().interpret("glob prok Gold is 36 Credits", context);
        assertEquals(9f, context.getQualityFactors().get("Gold"), 0.1f);
    }

    @Test
    public void noNumeralSymbols() throws InterpreterException {
        InterpreterContext context = new InterpreterContextFactory().create();
        new QualityInterpreter().interpret("Gold is 36", context);
        assertEquals(36f, context.getQualityFactors().get("Gold"), 0.1f);
    }

    @Test(expected = InterpreterException.class)
    public void invalidRomanFormat() throws InterpreterException {
        InterpreterContext context = new InterpreterContextFactory().create();
        context.getItemToNumeralTranslation().put("glob", "I");
        context.getItemToNumeralTranslation().put("prok", "V");
        new QualityInterpreter().interpret("glob glob prok Gold is 36 Credits", context);
    }

    @Test(expected = InterpreterException.class)
    public void noQuality() throws InterpreterException {
        InterpreterContext context = new InterpreterContextFactory().create();
        context.getItemToNumeralTranslation().put("glob", "I");
        context.getItemToNumeralTranslation().put("prok", "V");
        new QualityInterpreter().interpret("glob prok is 36 Credits", context);
    }
}