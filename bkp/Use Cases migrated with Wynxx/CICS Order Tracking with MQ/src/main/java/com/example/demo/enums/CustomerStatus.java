package com.example.demo.enums;

public enum CustomerStatus {

    ACTIVE("Active"),
    INACTIVE("Inactive"),
    SUSPENDED("Suspended"),
    PENDING_VERIFICATION("Pending Verification");

    private final String displayName;

    CustomerStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean canPlaceOrder() {
        return this == ACTIVE;
    }

    public boolean canLogin() {
        return this == ACTIVE || this == PENDING_VERIFICATION;
    }
}
