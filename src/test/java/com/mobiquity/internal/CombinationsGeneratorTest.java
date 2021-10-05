package com.mobiquity.internal;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CombinationsGeneratorTest {
    final CombinationsGenerator generator = new CombinationsGenerator();

    @Test
    public void testGenerate() {
        List<int[]> combinations = generator.generate(1);
        //combinations - [0]
        assertEquals(1, combinations.size());

        combinations = generator.generate(2);
        //combinations - [0], [1], [0, 1]
        assertEquals(3, combinations.size());

        combinations = generator.generate(3);
        //combinations - [0], [1], [2], [0, 1], [0, 2], [1, 2], [0, 1, 2]
        assertEquals(7, combinations.size());
    }
}
