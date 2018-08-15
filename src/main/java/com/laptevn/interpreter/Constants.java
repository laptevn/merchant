package com.laptevn.interpreter;

import java.util.regex.Pattern;

final class Constants {
    public final static Pattern EXPRESSIONS_SEPARATOR_PATTERN = Pattern.compile("is");
    public final static Pattern WORDS_SEPARATOR_PATTERN = Pattern.compile(" ");
    public final static int EXPRESSION_PARTS_COUNT = 2;
}