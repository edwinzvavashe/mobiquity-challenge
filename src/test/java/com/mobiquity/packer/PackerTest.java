package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PackerTest {

    @Test
    public void testPack() throws APIException {
        String file = "src/test/resources/test.txt";
        String result = Packer.pack(file);
        String expected = "4\n" +
                "-\n" +
                "2,7\n" +
                "8,9\n";
        assertEquals(expected, result);
    }

    @Test
    public void testPackWithTwoColons() {
        String file = "src/test/resources/test-doublecolon.txt";
        assertThrows(APIException.class, () -> Packer.pack(file));
    }
}
