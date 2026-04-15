package com.example.demo.enums;

public enum ShippingMethod {

    STANDARD("Standard Shipping"),
    EXPRESS("Express Shipping"),
    OVERNIGHT("Overnight Shipping"),
    SAME_DAY("Same Day Delivery"),
    PICKUP_IN_STORE("Pickup in Store"),
    FREE_SHIPPING("Free Shipping");

    private final String displayName;

    ShippingMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean requiresAddress() {
        return this != PICKUP_IN_STORE;
    }

    public boolean isPremium() {
        return this == EXPRESS || this == OVERNIGHT || this == SAME_DAY;
    }
}
