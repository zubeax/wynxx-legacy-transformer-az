package com.example.demo.enums;

public enum PipelineStatus {

    ACTIVE("Active"),
    INACTIVE("Inactive"),
    DRAFT("Draft"),
    ARCHIVED("Archived"),
    ERROR("Error"),
    PAUSED("Paused");

    private final String displayName;

    PipelineStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isRunnable() {
        return this == ACTIVE;
    }

    public boolean isEditable() {
        return this == DRAFT || this == INACTIVE || this == PAUSED;
    }

    public boolean canBeActivated() {
        return this == INACTIVE || this == DRAFT || this == PAUSED || this == ERROR;
    }
}
