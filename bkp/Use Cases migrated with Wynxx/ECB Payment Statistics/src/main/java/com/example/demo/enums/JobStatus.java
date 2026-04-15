package com.example.demo.enums;

public enum JobStatus {

    PENDING("Pending"),
    QUEUED("Queued"),
    RUNNING("Running"),
    COMPLETED("Completed"),
    FAILED("Failed"),
    CANCELLED("Cancelled"),
    PAUSED("Paused"),
    RETRYING("Retrying");

    private final String displayName;

    JobStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isTerminal() {
        return this == COMPLETED || this == FAILED || this == CANCELLED;
    }

    public boolean isActive() {
        return this == RUNNING || this == RETRYING;
    }

    public boolean canBeCancelled() {
        return this == PENDING || this == QUEUED || this == RUNNING || this == PAUSED || this == RETRYING;
    }

    public boolean canBeRetried() {
        return this == FAILED;
    }
}
