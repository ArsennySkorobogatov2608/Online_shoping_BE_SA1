package com.shop.exception;

public class ProductNotFoundException extends BaseShopException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}