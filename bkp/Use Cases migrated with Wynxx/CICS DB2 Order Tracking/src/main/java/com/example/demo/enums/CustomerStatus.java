package com.example.demo.enums;

public enum CustomerStatus {

    ACTIVE("Active"),
    INACTIVE("Inactive"),
    SUSPENDED("Suspended"),
    PENDING_VERIFICATION("Pending Verification"),
    BLACKLISTED("Blacklisted");

    private final String displayName;

    CustomerStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean canPlaceOrders() {
        return this == ACTIVE;
    }

    public boolean canLogin() {
        return this == ACTIVE || this == PENDING_VERIFICATION;
    }

    public boolean isRestricted() {
        return this == SUSPENDED || this == BLACKLISTED;
    }
}
