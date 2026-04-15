package com.example.demo.enums;

public enum ProductStatus {

    ACTIVE("Active"),
    INACTIVE("Inactive"),
    OUT_OF_STOCK("Out of Stock"),
    DISCONTINUED("Discontinued"),
    DRAFT("Draft");

    private final String displayName;

    ProductStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isOrderable() {
        return this == ACTIVE;
    }

    public boolean isVisible() {
        return this == ACTIVE || this == OUT_OF_STOCK;
    }
}
