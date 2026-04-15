package com.example.demo.enums;

public enum OrderStatus {

    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    PROCESSING("Processing"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled"),
    REFUNDED("Refunded"),
    ON_HOLD("On Hold");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean canBeCancelled() {
        return this == PENDING || this == CONFIRMED || this == PROCESSING || this == ON_HOLD;
    }

    public boolean canBeShipped() {
        return this == CONFIRMED || this == PROCESSING;
    }

    public boolean canBeConfirmed() {
        return this == PENDING || this == ON_HOLD;
    }

    public boolean canBeRefunded() {
        return this == DELIVERED || this == SHIPPED;
    }

    public boolean isTerminal() {
        return this == DELIVERED || this == CANCELLED || this == REFUNDED;
    }
}
