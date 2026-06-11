package com.shop.enums;

public enum PaymentStatus {
    UNPAID("Не оплачен"),
    PAID("Оплачен"),
    REFUNDED("Возврат");

    private final String label;

    PaymentStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}