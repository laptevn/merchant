package com.laptevn.interpreter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AssignmentInterpreterTest {
    @Test
    public void wrongNumberOfParts() throws InterpreterException {
        assertFalse(new AssignmentInterpreter().interpret("asd asd", new InterpreterContextFactory().create()));
    }

    @Test
    public void noLeftPart() throws InterpreterException {
        assertFalse(new AssignmentInterpreter().interpret("is X", new InterpreterContextFactory().create()));
    }

    @Test
    public void tooManyLeftParts() throws InterpreterException {
        assertFalse(new AssignmentInterpreter().interpret("glob glob Silver is X", new InterpreterContextFactory().create()));
    }

    @Test
    public void noRightPart() throws InterpreterException {
        assertFalse(new AssignmentInterpreter().interpret("glob is", new InterpreterContextFactory().create()));
    }

    @Test
    public void tooManyRightParts() throws InterpreterException {
        assertFalse(new AssignmentInterpreter().interpret("glob is X L", new InterpreterContextFactory().create()));
    }

    @Test(expected = InterpreterException.class)
    public void invalidRomanNumeral() throws InterpreterException {
        new AssignmentInterpreter().interpret("glob is P", new InterpreterContextFactory().create());
    }

    @Test
    public void correctAssignment() throws InterpreterException {
        InterpreterContext context = new InterpreterContextFactory().create();
        assertTrue(new AssignmentInterpreter().interpret("glob is X", context));
        assertEquals("X", context.getItemToNumeralTranslation().get("glob"));
    }
}