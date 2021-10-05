package com.mobiquity.internal;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PackageCombinationTest {

    @Test
    public void testIsBetterCombinationThanWithSameValueDifferentWeight() {
        final int[] firstCombination = new int[] {0, 1};
        final int[] secondCombination = new int[] {2, 3};
        final PackageItem[] allPackageItems = new PackageItem[4];
        allPackageItems[0] = new PackageItem(new BigDecimal(20), new BigDecimal(75));
        allPackageItems[1] = new PackageItem(new BigDecimal(55), new BigDecimal(75));
        allPackageItems[2] = new PackageItem(new BigDecimal(10), new BigDecimal(75));
        allPackageItems[3] = new PackageItem(new BigDecimal(55), new BigDecimal(75));

        PackageCombination firstPackageCombination = new PackageCombination(firstCombination, allPackageItems);
        PackageCombination secondPackageCombination = new PackageCombination(secondCombination, allPackageItems);
        assertTrue(secondPackageCombination.isBetterCombinationThan(firstPackageCombination));
    }

    @Test
    public void testIsBetterCombinationThanWithDifferentValueDifferentWeight() {
        final int[] firstCombination = new int[] {0, 1};
        final int[] secondCombination = new int[] {2, 3};
        final PackageItem[] allPackageItems = new PackageItem[4];
        allPackageItems[0] = new PackageItem(new BigDecimal(20), new BigDecimal(150));
        allPackageItems[1] = new PackageItem(new BigDecimal(55), new BigDecimal(75));
        allPackageItems[2] = new PackageItem(new BigDecimal(10), new BigDecimal(75));
        allPackageItems[3] = new PackageItem(new BigDecimal(55), new BigDecimal(75));

        PackageCombination firstPackageCombination = new PackageCombination(firstCombination, allPackageItems);
        PackageCombination secondPackageCombination = new PackageCombination(secondCombination, allPackageItems);
        assertFalse(secondPackageCombination.isBetterCombinationThan(firstPackageCombination));
    }
}
