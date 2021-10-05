package com.mobiquity.internal;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PackageItemTest {

    @Test
    public void testParseString() throws APIException {
        final int lineNumber = 1;
        final String itemStr = "1,15.3,€34";
        PackageItem item = PackageItem.parseString(itemStr, lineNumber);
        assertEquals(round(new BigDecimal("15.3")), item.getWeight());
        assertEquals(round(new BigDecimal("34")), item.getValue());
    }

    private BigDecimal round(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
