package com.example.demo.enums;

public enum PaymentMethod {

    CREDIT_CARD("Credit Card"),
    DEBIT_CARD("Debit Card"),
    BANK_TRANSFER("Bank Transfer"),
    PAYPAL("PayPal"),
    CASH_ON_DELIVERY("Cash on Delivery"),
    CRYPTOCURRENCY("Cryptocurrency"),
    STORE_CREDIT("Store Credit"),
    INVOICE("Invoice");

    private final String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean requiresOnlineProcessing() {
        return this == CREDIT_CARD || this == DEBIT_CARD || this == PAYPAL || this == CRYPTOCURRENCY;
    }

    public boolean isOfflinePayment() {
        return this == CASH_ON_DELIVERY || this == BANK_TRANSFER || this == INVOICE;
    }
}
