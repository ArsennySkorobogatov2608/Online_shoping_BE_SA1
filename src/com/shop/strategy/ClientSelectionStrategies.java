package com.shop.strategy;

import com.shop.enums.ClientStatus;
import com.shop.interfaces.ClientSelectionStrategy;
import com.shop.interfaces.PaymentStrategy;
import com.shop.model.Client;

public class ClientSelectionStrategies {

    public static class VIPClientSelectionStrategy implements ClientSelectionStrategy {
        private final double vipThreshold;
        private final PaymentStrategy defaultStrategy;

        public VIPClientSelectionStrategy(double vipThreshold, PaymentStrategy defaultStrategy) {
            this.vipThreshold = vipThreshold;
            this.defaultStrategy = defaultStrategy;
        }

        @Override
        public Client selectClient(int id, String name, double balance) {
            Client client = new Client(id, name, balance);
            if (balance >= vipThreshold) {
                client.setStrategy(defaultStrategy);
                client.setStatus(ClientStatus.ACTIVE);
            }
            return client;
        }
    }

    public static class StandardClientSelectionStrategy implements ClientSelectionStrategy {
        private final PaymentStrategy defaultStrategy;

        public StandardClientSelectionStrategy(PaymentStrategy defaultStrategy) {
            this.defaultStrategy = defaultStrategy;
        }

        @Override
        public Client selectClient(int id, String name, double balance) {
            Client client = new Client(id, name, balance);
            client.setStrategy(defaultStrategy);
            return client;
        }
    }
}