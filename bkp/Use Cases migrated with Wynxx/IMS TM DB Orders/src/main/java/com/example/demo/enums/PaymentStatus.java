package com.example.demo.enums;

public enum PaymentStatus {

    PENDING("Pending"),
    AUTHORIZED("Authorized"),
    PAID("Paid"),
    PARTIALLY_REFUNDED("Partially Refunded"),
    REFUNDED("Refunded"),
    FAILED("Failed"),
    CANCELLED("Cancelled");

    private final String displayName;

    PaymentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean canBeRefunded() {
        return this == PAID || this == PARTIALLY_REFUNDED;
    }

    public boolean isSuccessful() {
        return this == PAID || this == AUTHORIZED;
    }
}
