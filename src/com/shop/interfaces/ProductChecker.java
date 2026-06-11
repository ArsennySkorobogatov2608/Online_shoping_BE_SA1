package com.shop.interfaces;

import com.shop.model.Product;

@FunctionalInterface
public interface ProductChecker {
    boolean check(Product product);
}