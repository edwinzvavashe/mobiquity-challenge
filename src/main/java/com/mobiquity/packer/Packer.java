package com.mobiquity.packer;

import com.mobiquity.internal.*;
import com.mobiquity.exception.APIException;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Packer {

    private static final CombinationsGenerator generator = new CombinationsGenerator();
    private static final PackerTokenizer tokenizer = new PackerTokenizer();
    private static final String DASH = "-";
    private static final String NEW_LINE = "\n";
    private static final int MAX_PACKAGE_ITEMS = 15;
    public static final BigDecimal MAX_WEIGHT_PER_LINE = new BigDecimal(100);

    private Packer() {
    }

    public static String pack(String filePath) throws APIException {
        StringBuilder result = new StringBuilder();
        try(BufferedReader reader = new BufferedReader
                (new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))){
            String line;
            int lineNumber = 0;
            while (reader.ready()) {
                lineNumber = lineNumber + 1;
                line = reader.readLine();
                result.append(packLine(line, lineNumber)).append(NEW_LINE);
            }
        } catch (IOException e) {
            throw new APIException("Error occurred while reading file.");
        }
        System.out.println(result);
        return result.toString();
    }

    private static String packLine(String line, int lineNumber) throws APIException{
        final Tokens tokens = tokenizer.tokenize(line, lineNumber);
        final Token maxWeightToken = tokens.getMaxWeight();
        final BigDecimal maxWeight = new BigDecimal(maxWeightToken.getText());
        if (MAX_WEIGHT_PER_LINE.compareTo(maxWeight) < 0) {
            throw new APIException("lineNumber: " + lineNumber + ". Line weight above limit: " + maxWeight);
        }
        final int packageCount = tokens.getPackages().size();
        if (packageCount > MAX_PACKAGE_ITEMS) {
            throw new APIException("lineNumber: " + line + ". Too many package items: " + packageCount);
        }
        final PackageItem[] packageItems = new PackageItem[packageCount];
        int count = 0;
        for (Token t : tokens.getPackages()) {
            packageItems[count] = PackageItem.parseString(t.getText(), lineNumber);
            count = count + 1;
        }
        List<int[]> combinations = generator.generate(packageCount);

        PackageCombination bestCombination = null;
        PackageCombination currentCombination;
        for(int[] combination : combinations) {
            currentCombination = new PackageCombination(combination, packageItems);
            if(currentCombination.weighsLessThanOrEqual(maxWeight)
                    && currentCombination.isBetterCombinationThan(bestCombination)) {
                bestCombination = currentCombination;
            }
        }

        return bestCombination == null ? DASH : bestCombination.getCombinationStr();
    }
}

