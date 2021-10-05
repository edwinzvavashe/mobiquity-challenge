package com.mobiquity.internal;

import com.mobiquity.exception.APIException;

import java.util.*;

public class PackerTokenizer {
    private static final char START_PACKAGE_MARKER = '(';
    private static final char END_PACKAGE_MARKER = ')';
    private static final char COLON = ':';
    private static final char SPACE = ' ';

    public Tokens tokenize(String line, int lineNumber) throws APIException {
        Deque<String> openParentheses = new ArrayDeque<>();
        StringBuilder builder = new StringBuilder();
        final Tokens tokens = new Tokens();
        int position = 0;
        boolean colonAlreadySeen = false;
        for(char c : line.toCharArray()) {
            position = position + 1;
            if(c == COLON) {
                if (colonAlreadySeen) {
                    throw new APIException("lineNumber: " + lineNumber + ". Format issue. More than one colon. Position: " + position);
                }
                tokens.setMaxWeight(new Token(TokenType.LINE_WEIGHT, builder.toString(), lineNumber));
                builder = new StringBuilder();
                colonAlreadySeen = true;

            } else if (c == START_PACKAGE_MARKER) {
                assert openParentheses.isEmpty();
                openParentheses.push(START_PACKAGE_MARKER + "");
                builder = new StringBuilder();
            } else if (c == END_PACKAGE_MARKER) {
                if (openParentheses.isEmpty()) {
                    throw new APIException("lineNumber: " + lineNumber + ". Format issue. An unexpected ). Position: " + position);
                }
                openParentheses.pop();
                tokens.getPackages().add(new Token(TokenType.PACKAGE, builder.toString(), lineNumber));
            } else if (c != SPACE){
                builder.append(c);
            }
        }

        return tokens;
    }
}
