package com.laptevn.interpreter;

import com.laptevn.converter.RomanSymbolConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AssignmentInterpreterTest {
    @Test
    public void wrongNumberOfParts() throws InterpreterException {
        assertFalse(new AssignmentInterpreter().interpret("asd asd", createContext()));
    }

    @Test
    public void noLeftPart() throws InterpreterException {
        assertFalse(new AssignmentInterpreter().interpret("is X", createContext()));
    }

    @Test
    public void tooManyLeftParts() throws InterpreterException {
        assertFalse(new AssignmentInterpreter().interpret("glob glob Silver is X", createContext()));
    }

    @Test
    public void tooManyRightParts() throws InterpreterException {
        assertFalse(new AssignmentInterpreter().interpret("glob is X L", createContext()));
    }

    @Test(expected = InterpreterException.class)
    public void invalidRomanNumeral() throws InterpreterException {
        assertFalse(new AssignmentInterpreter().interpret("glob is P", createContext()));
    }

    @Test
    public void correctAssignment() throws InterpreterException {
        InterpreterContext context = createContext();
        assertTrue(new AssignmentInterpreter().interpret("glob is X", context));
        assertEquals("X", context.getItemToNumeralTranslation().get("glob"));
    }

    private static InterpreterContext createContext() {
        return new InterpreterContext(new RomanSymbolConverter());
    }
}