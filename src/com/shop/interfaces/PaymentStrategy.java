package com.shop.interfaces;

public interface PaymentStrategy {
    boolean processPayment(double amount);
    String getStrategyName();
}