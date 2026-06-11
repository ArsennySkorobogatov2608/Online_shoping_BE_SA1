package com.shop.service;

import com.shop.model.Order;
import com.shop.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static OrderService instance;
    private final List<Order> orders = new ArrayList<>();
    private final ProductRepository productRepository;

    private OrderService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static OrderService getInstance(ProductRepository productRepository) {
        if (instance == null) {
            instance = new OrderService(productRepository);
        }
        return instance;
    }

    public void addOrder(Order order) {
        orders.add(order);
        System.out.println("Заказ добавлен: " + order);
    }

    public void showOrders() {
        System.out.println("\nВсе заказы:");
        if (orders.isEmpty()) {
            System.out.println("Заказов нет");
            return;
        }
        orders.forEach(Order::showInfo);
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }
}