package com.example.demo.enums;

public enum PaymentStatus {

    PENDING("Pending"),
    AUTHORIZED("Authorized"),
    PAID("Paid"),
    PARTIALLY_PAID("Partially Paid"),
    FAILED("Failed"),
    REFUNDED("Refunded"),
    PARTIALLY_REFUNDED("Partially Refunded"),
    VOIDED("Voided");

    private final String displayName;

    PaymentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isPaid() {
        return this == PAID || this == PARTIALLY_PAID;
    }

    public boolean canBeRefunded() {
        return this == PAID || this == PARTIALLY_PAID;
    }

    public boolean isTerminal() {
        return this == REFUNDED || this == VOIDED || this == FAILED;
    }
}
