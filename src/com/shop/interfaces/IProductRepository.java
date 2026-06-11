package com.shop.interfaces;

import com.shop.comparator.ProductComparator;
import com.shop.interfaces.ProductChecker;
import com.shop.exception.ProductNotFoundException;
import com.shop.model.Product;
import java.util.ArrayList;

public interface IProductRepository {
    void add(Product product);
    ArrayList<Product> getAll();
    ArrayList<Product> getSortedProducts(ProductComparator comparator);
    boolean filterAndShow(ProductChecker checker);
    void findFirstByPrice(double minPrice) throws ProductNotFoundException;
    double getAveragePrice();
    void showMostExpensive();
    String getTitlesAsString();
    void showGroupedByType();
    void showCountByStatus();
    void showAll();
}