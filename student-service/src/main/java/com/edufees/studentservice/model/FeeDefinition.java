package com.edufees.studentservice.model;

import com.edufees.studentservice.constants.FeeType;

public class FeeDefinition {
    private FeeType type;
    private int amount;

    public FeeDefinition(FeeType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    // Getters
    public FeeType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }
}
