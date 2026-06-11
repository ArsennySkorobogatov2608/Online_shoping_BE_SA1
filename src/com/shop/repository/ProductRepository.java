package com.shop.repository;

import com.shop.comparator.ProductComparator;
import com.shop.exception.ProductNotFoundException;
import com.shop.enums.PaymentStatus;
import com.shop.interfaces.IProductRepository;
import com.shop.interfaces.ProductChecker;
import com.shop.model.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductRepository implements IProductRepository {
    private final ArrayList<Product> products = new ArrayList<>();

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public ArrayList<Product> getAll() {
        return new ArrayList<>(products);
    }

    @Override
    public ArrayList<Product> getSortedProducts(ProductComparator comparator) {
        ArrayList<Product> sorted = new ArrayList<>(products);
        Collections.sort(sorted, comparator);
        return sorted;
    }

    @Override
    public boolean filterAndShow(ProductChecker checker) {
        List<Product> result = products.stream()
                .filter(checker::check)
                .toList();
        result.forEach(Product::showInfo);
        return !result.isEmpty();
    }

    @Override
    public void findFirstByPrice(double minPrice) throws ProductNotFoundException {
        Optional<Product> found = products.stream()
                .filter(p -> p.getPrice() >= minPrice)
                .findFirst();
        if (found.isEmpty()) {
            throw new ProductNotFoundException("Товар с ценой от " + minPrice + " не найден.");
        }
        System.out.println("найдено: ");
        found.get().showInfo();
    }

    @Override
    public double getAveragePrice() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);
    }

    @Override
    public void showMostExpensive() {
        products.stream()
                .max((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
                .ifPresentOrElse(
                        p -> { System.out.print("самый дорогой: "); p.showInfo(); },
                        () -> System.out.println("список пуст.")
                );
    }

    @Override
    public String getTitlesAsString() {
        return products.stream()
                .map(Product::getTitle)
                .collect(Collectors.joining(", "));
    }

    @Override
    public void showGroupedByType() {
        Map<String, Long> grouped = products.stream()
                .collect(Collectors.groupingBy(p -> p.getClass().getSimpleName(), Collectors.counting()));
        System.out.println("группировка: " + grouped);
    }

    @Override
    public void showCountByStatus() {
        Map<PaymentStatus, Long> statusCount = products.stream()
                .collect(Collectors.groupingBy(Product::getPaymentStatus, Collectors.counting()));
        System.out.println("по статусу: " + statusCount);
    }

    @Override
    public void showAll() {
        System.out.println("\nвсе товары");
        products.forEach(Product::showInfo);
    }
}