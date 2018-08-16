package com.laptevn.interpreter.expression;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AssignmentInterpreterTest {
    private final InterpreterContextFactory interpreterContextFactory = new InterpreterContextFactory();

    @Test
    public void emptyText() throws InterpreterException {
        assertFalse(new AssignmentInterpreter().interpret("", interpreterContextFactory.create()));
    }

    @Test(expected = NullPointerException.class)
    public void nullText() throws InterpreterException {
        assertFalse(new AssignmentInterpreter().interpret(null, interpreterContextFactory.create()));
    }

    @Test
    public void wrongNumberOfParts() throws InterpreterException {
        assertFalse(new AssignmentInterpreter().interpret("asd asd", interpreterContextFactory.create()));
    }

    @Test
    public void noLeftPart() throws InterpreterException {
        assertFalse(new AssignmentInterpreter().interpret("is X", interpreterContextFactory.create()));
    }

    @Test
    public void tooManyLeftParts() throws InterpreterException {
        assertFalse(new AssignmentInterpreter().interpret("glob glob Silver is X", interpreterContextFactory.create()));
    }

    @Test
    public void noRightPart() throws InterpreterException {
        assertFalse(new AssignmentInterpreter().interpret("glob is", interpreterContextFactory.create()));
    }

    @Test
    public void tooManyRightParts() throws InterpreterException {
        assertFalse(new AssignmentInterpreter().interpret("glob is X L", interpreterContextFactory.create()));
    }

    @Test(expected = InterpreterException.class)
    public void invalidRomanNumeral() throws InterpreterException {
        new AssignmentInterpreter().interpret("glob is P", interpreterContextFactory.create());
    }

    @Test
    public void correctAssignment() throws InterpreterException {
        InterpreterContext context = interpreterContextFactory.create();
        assertTrue(new AssignmentInterpreter().interpret("glob is X", context));
        assertEquals("X", context.getItemToNumeralTranslation().get("glob"));
    }
}