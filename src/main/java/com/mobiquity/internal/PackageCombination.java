package com.mobiquity.internal;

import java.math.BigDecimal;

public class PackageCombination implements Comparable<PackageCombination>{
    private static final int INDEX_OFFSET = 1;
    public static final String COMMA = ",";
    private BigDecimal totalWeight = BigDecimal.ZERO;
    private BigDecimal totalValue = BigDecimal.ZERO;
    private String combinationStr = "";

    public PackageCombination(final int[] combination, final PackageItem[] allPackageItems) {
        initialise(combination, allPackageItems);
    }

    private void initialise(final int[] combination, final PackageItem[] allPackageItems) {
        PackageItem p;
        final StringBuilder builder = new StringBuilder();
        int packageItemIndex;
        for(int i : combination) {
            // offset is needed since packages item index values start from 1 not 0
            packageItemIndex = i + INDEX_OFFSET;
            p = allPackageItems[i];
            this.totalWeight = this.totalWeight.add(p.getWeight());
            this.totalValue = this.totalValue.add(p.getValue());
            if (!builder.toString().isEmpty()) {
                builder.append(COMMA);
            }
            builder.append(packageItemIndex);
        }
        combinationStr = builder.toString();
    }

    @Override
    public int compareTo(PackageCombination other) {
        if(other == null) {
            return 1;
        }
        if(this.totalValue.compareTo(other.totalValue) != 0){
            return this.totalValue.compareTo(other.totalValue);
        }
        //for equal value, this combination is considered better
        //if it's weight is less than the other weight
        return other.totalWeight.compareTo(this.totalWeight);
    }

    public boolean isBetterCombinationThan(PackageCombination other) {
        return this.compareTo(other) > 0;
    }

    public boolean weighsLessThanOrEqual(BigDecimal weight) {
        return this.totalWeight.compareTo(weight) < 0;
    }

    public String getCombinationStr() {
        return combinationStr;
    }
}
