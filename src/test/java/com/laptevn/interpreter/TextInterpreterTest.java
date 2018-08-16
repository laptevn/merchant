package com.laptevn.interpreter;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TextInterpreterTest {
    private final TextInterpreterFactory textInterpreterFactory = new TextInterpreterFactory();

    @Test
    public void exampleInterpretation() {
        String[] input = new String[] {
                "glob is I",
                "prok is V",
                "pish is X",
                "tegj is L",
                "glob glob Silver is 34 Credits",
                "glob prok Gold is 57800 Credits",
                "pish pish Iron is 3910 Credits",
                "how much is pish tegj glob glob ?",
                "how many Credits is glob prok Silver ?",
                "how many Credits is glob prok Gold ?",
                "how many Credits is glob prok Iron ?",
                "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"
        };
        String[] actualOutput = textInterpreterFactory.create().interpret(input);

        String[] expectedOutput = new String[] {
                "pish tegj glob glob is 42",
                "glob prok Silver is 68 Credits",
                "glob prok Gold is 57800 Credits",
                "glob prok Iron is 782 Credits",
                "I have no idea what you are talking about"
        };
        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    public void noLinesToInterpret() {
        String[] output = textInterpreterFactory.create().interpret(new String[0]);
        assertEquals(0, output.length);
    }

    @Test(expected = NullPointerException.class)
    public void nullInput() {
        textInterpreterFactory.create().interpret(null);
    }

    @Test
    public void errorInInterpretation() {
        String[] input = new String[] {
                "glob is I",
                "prok is VI",
                "how much is glob ?"
        };
        String[] actualOutput = textInterpreterFactory.create().interpret(input);

        String[] expectedOutput = new String[] {
                "Error: Value can be only Roman numeral symbol",
                "glob is 1"
        };
        assertArrayEquals(expectedOutput, actualOutput);
    }
}