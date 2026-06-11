package com.shop.exception;

public class ProductAlreadyPaidException extends BaseShopException {
    public ProductAlreadyPaidException(String message) {
        super(message);
    }
}