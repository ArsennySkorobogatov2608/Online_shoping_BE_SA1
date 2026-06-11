package com.shop.model;

public class ProductTypes {

    public static class Electronic extends Product {
        public Electronic(String title, double price, String description) {
            super(title, price, description);
        }

        @Override
        public void showInfo() {
            System.out.println("электроника id: " + getId() +
                    "   название: " + getTitle() +
                    "   цена: " + getPrice() +
                    "   описание: " + getDescription());
        }
    }

    public static class MobileDevice extends Electronic {
        public MobileDevice(String title, double price, String description) {
            super(title, price, description);
        }

        @Override
        public void showInfo() {
            System.out.println("мобильные устройства id: " + getId() +
                    "   название: " + getTitle() +
                    "   цена: " + getPrice() +
                    "   описание: " + getDescription());
        }
    }

    public static class GardenItem extends Product {
        public GardenItem(String title, double price, String description) {
            super(title, price, description);
        }

        @Override
        public void showInfo() {
            System.out.println("[дом и сад] id: " + getId() +
                    " | название: " + getTitle() +
                    " | цена: " + getPrice() +
                    " | описание: " + getDescription());
        }
    }
}