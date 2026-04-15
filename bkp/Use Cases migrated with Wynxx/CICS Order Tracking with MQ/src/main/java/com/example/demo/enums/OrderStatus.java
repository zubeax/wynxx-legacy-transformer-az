package com.example.demo.enums;

public enum OrderStatus {

    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    PROCESSING("Processing"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled"),
    REFUNDED("Refunded");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean canBeCancelled() {
        return this == PENDING || this == CONFIRMED || this == PROCESSING;
    }

    public boolean canBeShipped() {
        return this == CONFIRMED || this == PROCESSING;
    }

    public boolean canBeDelivered() {
        return this == SHIPPED;
    }

    public boolean canBeRefunded() {
        return this == DELIVERED;
    }

    public boolean isTerminal() {
        return this == CANCELLED || this == DELIVERED || this == REFUNDED;
    }
}
