package com.example.demo.enums;

public enum DataSourceStatus {

    ACTIVE("Active"),
    INACTIVE("Inactive"),
    ERROR("Error"),
    PENDING("Pending"),
    TESTING("Testing"),
    DEPRECATED("Deprecated");

    private final String displayName;

    DataSourceStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isOperational() {
        return this == ACTIVE;
    }

    public boolean canBeActivated() {
        return this == INACTIVE || this == ERROR || this == PENDING;
    }

    public boolean canBeDeleted() {
        return this == INACTIVE || this == DEPRECATED;
    }
}
