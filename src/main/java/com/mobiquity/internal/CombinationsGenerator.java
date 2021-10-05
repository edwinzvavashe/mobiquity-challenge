package com.mobiquity.internal;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CombinationsGenerator {

    public List<int[]> generate(final int number) {
        final List<int[]> combinations =  new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            final Iterator<int[]> iterator = CombinatoricsUtils.combinationsIterator(number, i);
            while (iterator.hasNext()) {
                final int[] combination = iterator.next();
                combinations.add(combination);
            }
        }
        return combinations;
    }
}
