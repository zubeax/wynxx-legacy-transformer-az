package com.example.demo.enums;

public enum PaymentMethod {

    CREDIT_CARD("Credit Card"),
    DEBIT_CARD("Debit Card"),
    BANK_TRANSFER("Bank Transfer"),
    PAYPAL("PayPal"),
    CASH_ON_DELIVERY("Cash on Delivery"),
    VOUCHER("Voucher");

    private final String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean requiresOnlineAuthorization() {
        return this == CREDIT_CARD || this == DEBIT_CARD || this == PAYPAL;
    }
}
