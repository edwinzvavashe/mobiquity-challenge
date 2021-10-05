package com.mobiquity.internal;

public class Token {
    private final TokenType type;
    private final String text;
    private final int lineNumber;

    public Token(final TokenType type, final String text, final int lineNumber) {
        this.type = type;
        this.text = text;
        this.lineNumber = lineNumber;
    }

    public TokenType getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
