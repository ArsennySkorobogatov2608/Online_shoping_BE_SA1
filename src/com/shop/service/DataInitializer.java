package com.shop.service;

import com.shop.enums.ClientStatus;
import com.shop.factory.ClientFactory;
import com.shop.model.Catalog;
import com.shop.model.Client;
import com.shop.model.ProductTypes;
import com.shop.repository.ProductRepository;
import com.shop.strategy.ClientSelectionStrategies;
import com.shop.strategy.PaymentStrategies;

public class DataInitializer {

    public static Client seedData(Catalog catalog, ProductRepository repository) {
        ClientFactory.setSelectionStrategy(
                new ClientSelectionStrategies.VIPClientSelectionStrategy(150000.0, new PaymentStrategies.CardPaymentStrategy())
        );

        catalog.addCategory("Электроника");
        catalog.addSubCategory("Смартфоны");
        catalog.addSubCategory("Ноутбуки");
        catalog.addCategory("Дом и сад");
        catalog.addSubCategory("Инструменты");


        repository.add(new ProductTypes.MobileDevice("iPhone 15", 90000.0, "Новинка от Apple"));
        repository.add(new ProductTypes.MobileDevice("Samsung S24", 85000.0, "Флагман на Android"));
        repository.add(new ProductTypes.GardenItem("Газонокосилка", 15000.0, "Бензиновая, мощная"));
        repository.add(new ProductTypes.Electronic("MacBook Air", 120000.0, "Чип M2, 13 дюймов"));

        return ClientFactory.createClient(101, "Алексей", 200000.0);
    }
}