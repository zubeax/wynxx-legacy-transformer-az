package com.example.demo.enums;

public enum ShippingMethod {

    STANDARD("Standard Shipping"),
    EXPRESS("Express Shipping"),
    OVERNIGHT("Overnight Shipping"),
    PICKUP("In-Store Pickup"),
    FREE("Free Shipping");

    private final String displayName;

    ShippingMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getEstimatedDays() {
        return switch (this) {
            case STANDARD -> 5;
            case EXPRESS -> 2;
            case OVERNIGHT -> 1;
            case PICKUP -> 0;
            case FREE -> 7;
        };
    }
}
