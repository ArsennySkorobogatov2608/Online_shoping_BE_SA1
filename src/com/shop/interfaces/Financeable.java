package com.shop.interfaces;

public interface Financeable {
    double checkBalance();
    boolean hasEnoughMoney(double amount);
    String getFinancialStatus();
}