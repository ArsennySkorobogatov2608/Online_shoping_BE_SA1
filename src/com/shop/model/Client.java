package com.shop.model;

import com.shop.enums.ClientStatus;
import com.shop.enums.PaymentStatus;
import com.shop.exception.BaseShopException;
import com.shop.exception.ClientBlockedException;
import com.shop.exception.InsufficientFundsException;
import com.shop.exception.InvalidPaymentException;
import com.shop.exception.ProductAlreadyPaidException;
import com.shop.interfaces.Financeable;
import com.shop.interfaces.PaymentStrategy;
import java.util.ArrayList;
import java.util.List;

public class Client implements Financeable {
    private double balance;
    private int number;
    private String name;
    private ClientStatus clientStatus;
    private PaymentStrategy paymentStrategy;
    private final List<ImmutableReceipt> transactionHistory = new ArrayList<>();

    public Client(int number, String name, double initialBalance) {
        this.number = number;
        this.name = name;
        this.balance = initialBalance;
        this.clientStatus = ClientStatus.NEW;
        this.paymentStrategy = new com.shop.strategy.PaymentStrategies.CardPaymentStrategy();
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    public void setStatus(ClientStatus status) {
        this.clientStatus = status;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Баланс пополнен на %.2f%n", amount);
        }
    }

    @Override
    public double checkBalance() {
        return balance;
    }

    @Override
    public boolean hasEnoughMoney(double amount) {
        return balance >= amount;
    }

    @Override
    public String getFinancialStatus() {
        return String.format("Клиент #%d (%s) [%s] | Баланс: %.2f", number, name, clientStatus.getLabel(), balance);
    }

    public boolean purchase(Product product) throws BaseShopException {
        if (clientStatus == ClientStatus.BLOCKED) {
            ImmutableReceipt receipt = new ImmutableReceipt(name, product.getTitle(), product.getFinalPrice(),
                    paymentStrategy.getStrategyName(), false, "Клиент заблокирован");
            transactionHistory.add(receipt);
            receipt.printReceipt();
            throw new ClientBlockedException("Клиент \"" + name + "\" заблокирован. Операция отклонена.");
        }
        if (product.isPaid()) {
            ImmutableReceipt receipt = new ImmutableReceipt(name, product.getTitle(), product.getFinalPrice(),
                    paymentStrategy.getStrategyName(), false, "Товар уже оплачен");
            transactionHistory.add(receipt);
            receipt.printReceipt();
            throw new ProductAlreadyPaidException("Товар \"" + product.getTitle() + "\" уже оплачен.");
        }
        double price = product.getFinalPrice();
        if (!hasEnoughMoney(price)) {
            ImmutableReceipt receipt = new ImmutableReceipt(name, product.getTitle(), price,
                    paymentStrategy.getStrategyName(), false,
                    String.format("Недостаточно средств: нужно %.2f, доступно %.2f", price, balance));
            transactionHistory.add(receipt);
            receipt.printReceipt();
            throw new InsufficientFundsException(
                    String.format("Недостаточно средств для \"%s\". Нужно: %.2f, есть: %.2f",
                            product.getTitle(), price, balance));
        }
        if (!paymentStrategy.processPayment(price)) {
            ImmutableReceipt receipt = new ImmutableReceipt(name, product.getTitle(), price,
                    paymentStrategy.getStrategyName(), false, "Ошибка обработки платежа");
            transactionHistory.add(receipt);
            receipt.printReceipt();
            throw new InvalidPaymentException("Ошибка обработки платежа за товар \"" + product.getTitle() + "\".");
        }
        balance -= price;
        product.pay(price);
        ImmutableReceipt receipt = new ImmutableReceipt(name, product.getTitle(), price, paymentStrategy.getStrategyName());
        transactionHistory.add(receipt);
        System.out.printf("Списание: %.2f. Остаток: %.2f%n", price, balance);
        receipt.printReceipt();
        return true;
    }

    public void showHistory() {
        System.out.println("\nистория транзакций клиента " + name + ":");
        if (transactionHistory.isEmpty()) {
            System.out.println("пусто.");
            return;
        }
        double totalSpent = 0.0;
        for (ImmutableReceipt receipt : transactionHistory) {
            receipt.printReceipt();
            if (receipt.isSuccess()) {
                totalSpent += receipt.getAmount();
            }
        }
        System.out.printf("────────────────────────────────────────────────────────────%n");
        System.out.printf("ОБЩАЯ СУММА: %.2f ₽%n", totalSpent);
    }

    @Override
    public String toString() {
        return "Client{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", status=" + clientStatus.getLabel() +
                '}';
    }
}