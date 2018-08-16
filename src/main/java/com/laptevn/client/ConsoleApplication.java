package com.laptevn.client;

import com.laptevn.interpreter.TextInterpreterFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Console application for testing purposes.
 */
public class ConsoleApplication {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Only file name with input data is allowed as a parameter");
            return;
        }

        String[] input = Files.readAllLines(Paths.get(args[0])).toArray(new String[0]);
        String[] output = new TextInterpreterFactory().create().interpret(input);
        for (String line: output) {
            System.out.println(line);
        }
    }
}