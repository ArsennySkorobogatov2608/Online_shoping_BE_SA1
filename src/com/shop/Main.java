package com.shop;

import com.shop.model.Catalog;
import com.shop.model.Client;
import com.shop.repository.ProductRepository;
import com.shop.service.DataInitializer;
import com.shop.service.OrderService;
import com.shop.service.ShopApplication;

public class Main {

    public static void main(String[] args) {
        Catalog catalog = Catalog.getInstance();
        ProductRepository productRepository = new ProductRepository();
        OrderService orderService = OrderService.getInstance(productRepository);

        Client demoClient = DataInitializer.seedData(catalog, productRepository);

        ShopApplication app = new ShopApplication(productRepository, orderService, demoClient);
        app.run();
    }
}