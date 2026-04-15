package com.example.demo.enums;

public enum AggregationResultStatus {

    SUCCESS("Success"),
    PARTIAL("Partial Success"),
    FAILED("Failed"),
    EMPTY("Empty Result"),
    SKIPPED("Skipped");

    private final String displayName;

    AggregationResultStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isSuccessful() {
        return this == SUCCESS || this == PARTIAL;
    }

    public boolean hasData() {
        return this == SUCCESS || this == PARTIAL;
    }

    public boolean requiresInvestigation() {
        return this == FAILED || this == PARTIAL;
    }
}
