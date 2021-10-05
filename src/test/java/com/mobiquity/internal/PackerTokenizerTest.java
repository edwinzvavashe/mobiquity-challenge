package com.mobiquity.internal;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PackerTokenizerTest {
    private final PackerTokenizer tokenizer = new PackerTokenizer();

    @Test
    public void testTokenizeWithCorrectlyFormattedInput() throws APIException {
        String line = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        int lineNumber = 1;
        Tokens tokens = tokenizer.tokenize(line, lineNumber);
        assertEquals("81", tokens.getMaxWeight().getText());
        assertEquals(6, tokens.getPackages().size());
        assertEquals("1,53.38,€45", tokens.getPackages().get(0).getText());
        assertEquals("6,46.34,€48", tokens.getPackages().get(5).getText());
    }

    @Test
    public void testTokenizeWithDoubleClosingParenthesis() {
        // spot the double closing parenthesis
        String line = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3)) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        int lineNumber = 1;
        assertThrows(APIException.class, () -> tokenizer.tokenize(line, lineNumber));
    }

    @Test
    public void testTokenizeWithTwoColons() {
        // spot the double closing parenthesis
        String line = "81 : (1,53.38,€45) : (2,88.62,€98) (3,78.48,€3)) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        int lineNumber = 1;
        assertThrows(APIException.class, () -> tokenizer.tokenize(line, lineNumber));
    }
}
