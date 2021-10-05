package com.mobiquity.internal;

import java.util.ArrayList;
import java.util.List;

public class Tokens {
    private Token maxWeight = null;
    private List<Token> packages = new ArrayList<>();

    public void setMaxWeight(Token maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Token getMaxWeight() {
        return maxWeight;
    }

    public List<Token> getPackages() {
        return packages;
    }
}
