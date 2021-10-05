package com.mobiquity.internal;

import com.mobiquity.exception.APIException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PackageItem {
    public static final String COMMA = ",";
    public static final String CURRENCY = "€";
    public static final String BLANK = "";
    public static final int EXPECTED_PARTS_COUNT = 3;
    private final BigDecimal weight;
    private final BigDecimal value;

    public PackageItem(final BigDecimal weight, final BigDecimal value) {
        this.weight = round(weight);
        this.value = round(value);
    }

    public static PackageItem parseString(final String str, final int lineNumber) throws APIException {
        String[] strArray = str.split(COMMA);
        if (strArray.length != EXPECTED_PARTS_COUNT) {
            throw new APIException("lineNumber: " + lineNumber + ". Package format issue: " + str);
        }
        final String weightStr = strArray[1];
        final BigDecimal weight = round(new BigDecimal(weightStr));
        final String valueStr = strArray[2].replace(CURRENCY, BLANK);
        final BigDecimal value = round(new BigDecimal(valueStr));
        return new PackageItem(weight, value);
    }

    private static BigDecimal round(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public BigDecimal getValue() {
        return value;
    }
}
