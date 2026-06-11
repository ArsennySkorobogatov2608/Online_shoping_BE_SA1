package com.shop.strategy;

import com.shop.interfaces.PaymentStrategy;

public class PaymentStrategies {

    public static class CardPaymentStrategy implements PaymentStrategy {
        @Override
        public boolean processPayment(double amount) {
            System.out.println("Обработка оплаты картой: " + amount + " ₽");
            return true;
        }

        @Override
        public String getStrategyName() {
            return "Банковская карта";
        }
    }

    public static class CashPaymentStrategy implements PaymentStrategy {
        @Override
        public boolean processPayment(double amount) {
            System.out.println("Обработка оплаты наличными: " + amount + " ₽");
            return true;
        }

        @Override
        public String getStrategyName() {
            return "Наличные";
        }
    }
}