package com.example.demo.enums;

public enum JobType {

    FULL_SYNC("Full Synchronization"),
    INCREMENTAL("Incremental Sync"),
    REAL_TIME("Real-Time Streaming"),
    SNAPSHOT("Snapshot"),
    DELTA("Delta Extraction"),
    VALIDATION("Data Validation"),
    TRANSFORMATION("Data Transformation");

    private final String displayName;

    JobType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isStreaming() {
        return this == REAL_TIME;
    }

    public boolean requiresCheckpoint() {
        return this == INCREMENTAL || this == DELTA;
    }

    public boolean isFullLoad() {
        return this == FULL_SYNC || this == SNAPSHOT;
    }
}
