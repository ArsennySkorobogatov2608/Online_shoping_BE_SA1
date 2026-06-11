package com.shop.service;

import com.shop.comparator.ProductComparator;
import com.shop.exception.BaseShopException;
import com.shop.exception.ClientBlockedException;
import com.shop.exception.InsufficientFundsException;
import com.shop.exception.InvalidPaymentException;
import com.shop.exception.OrderCreationException;
import com.shop.exception.ProductAlreadyPaidException;
import com.shop.factory.OrderFactory;
import com.shop.interfaces.ProductChecker;
import com.shop.model.Catalog;
import com.shop.model.Client;
import com.shop.model.Order;
import com.shop.model.Product;
import com.shop.model.ProductTypes;
import com.shop.repository.ProductRepository;
import com.shop.strategy.PaymentStrategies;
import java.util.ArrayList;
import java.util.Scanner;

public class ShopApplication {
    private final Scanner scanner;
    private final ProductRepository repository;
    private final OrderService orderService;
    private final Client client;
    private boolean running;

    public ShopApplication(ProductRepository repository, OrderService orderService, Client client) {
        this.scanner = new Scanner(System.in);
        this.repository = repository;
        this.orderService = orderService;
        this.client = client;
        this.running = true;
    }

    public void run() {
        while (running) {
            showMenu();
            int choice = getIntInput("\nВыберите опцию: ");

            try {
                switch (choice) {
                    case 1 -> Catalog.getInstance().showCatalog();
                    case 2 -> repository.showAll();
                    case 3 -> sortProducts();
                    case 4 -> compareProducts();
                    case 5 -> processPayment();
                    case 6 -> filterWithLambda();
                    case 7 -> streamOperations();
                    case 8 -> orderService.showOrders();
                    case 9 -> client.showHistory();
                    case 10 -> {
                        System.out.println("Конец работы.");
                        running = false;
                    }

                    default -> System.out.println("Неверный выбор, попробуйте снова.");
                }
            } catch (BaseShopException e) {
                System.out.println("ОШИБКА МАГАЗИНА: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("НЕПРЕДВИДЕННАЯ ОШИБКА: " + e.getMessage());
            } finally {
                if (running) {
                    System.out.print("\nНажмите Enter для продолжения...");
                    scanner.nextLine();
                }
            }
        }
    }

    private void showMenu() {
        System.out.println("""
                \nМЕНЮ ОНЛАЙН-МАГАЗИНА
                1. Показать каталог
                2. Все товары
                3. Сортировать товары
                4. Сравнить товары
                5. Тест оплаты 
                6. Фильтрация (lambda)
                7. Операции Stream API
                8. Просмотр заказов
                9. История транзакций (чеки)
                10. Выход
                """);
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Введите целое число!");
            scanner.next();
            System.out.print(prompt);
        }
        int val = scanner.nextInt();
        scanner.nextLine(); // очистка буфера
        return val;
    }

    private double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Введите число (можно дробное)!");
            scanner.next();
            System.out.print(prompt);
        }
        double val = scanner.nextDouble();
        scanner.nextLine(); // очистка буфера
        return val;
    }

    private void sortProducts() {
        System.out.println("""
                \nСОРТИРОВКА
                1. По названию (А-Я)
                2. По названию (Я-А)
                3. По цене (дешёвые -> дорогие)
                4. По цене (дорогие -> дешёвые)
                5. По ID
                """);

        int choice = getIntInput("Выбор: ");
        ProductComparator.SortBy sortBy;
        boolean ascending = true;

        switch (choice) {
            case 1 -> sortBy = ProductComparator.SortBy.TITLE;
            case 2 -> { sortBy = ProductComparator.SortBy.TITLE; ascending = false; }
            case 3 -> sortBy = ProductComparator.SortBy.PRICE;
            case 4 -> { sortBy = ProductComparator.SortBy.PRICE; ascending = false; }
            default -> sortBy = ProductComparator.SortBy.ID;
        }

        ProductComparator comparator = new ProductComparator(sortBy, ascending);
        ArrayList<Product> sorted = repository.getSortedProducts(comparator);

        System.out.println("\nОтсортировано:");
        for (int i = 0; i < sorted.size(); i++) {
            System.out.print((i + 1) + ". ");
            sorted.get(i).showInfo();
        }
    }

    private void compareProducts() {
        System.out.println("\nСравнение");
        System.out.println("Доступные товары:");
        ArrayList<Product> products = repository.getAll();
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.printf("%d. %-20s [%s]%n", i + 1, p.getTitle(), p.getClass().getSimpleName());
        }

        int idx1 = getIntInput("\nПервый товар (номер): ") - 1;
        int idx2 = getIntInput("Второй товар (номер): ") - 1;

        if (idx1 < 0 || idx1 >= products.size() || idx2 < 0 || idx2 >= products.size()) {
            System.out.println("Неверный номер!");
            return;
        }

        Product p1 = products.get(idx1);
        Product p2 = products.get(idx2);

        if (!p1.canCompareWith(p2)) {
            System.out.println("\nНельзя сравнивать разные типы товаров!");
            System.out.println("   " + p1.getTitle() + " - " + p1.getClass().getSimpleName());
            System.out.println("   " + p2.getTitle() + " - " + p2.getClass().getSimpleName());
            return;
        }

        int result = p1.compareTo(p2);
        System.out.println("\nРезультат:");
        System.out.println(p1.getTitle() + " цена: " + p1.getPrice());
        System.out.println(p2.getTitle() + " цена: " + p2.getPrice());

        if (result < 0) System.out.println("- \"" + p1.getTitle() + "\" идёт раньше по алфавиту");
        else if (result > 0) System.out.println("- \"" + p2.getTitle() + "\" идёт раньше по алфавиту");
        else System.out.println("Названия одинаковые");

        System.out.println("\nДополнительно:");
        System.out.println("equals(): " + p1.equals(p2));
        System.out.println("hashCode: " + p1.hashCode() + " и " + p2.hashCode());
    }

    private void processPayment() {
        System.out.println("\nпокупка");
        System.out.println(client.getFinancialStatus());
        System.out.println("текущий баланс: " + client.checkBalance());

        System.out.println("\nвыберите способ оплаты:");
        System.out.println("1. Банковская карта");
        System.out.println("2. Наличные");
        int strategyChoice = getIntInput("выбор: ");
        client.setStrategy(strategyChoice == 1 ? new PaymentStrategies.CardPaymentStrategy() : new PaymentStrategies.CashPaymentStrategy());

        ArrayList<Product> products = repository.getAll();
        System.out.println("\nвыберите товар для покупки:");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.printf("%d. %-20s - %.2f - %s%n",
                    i + 1, p.getTitle(), p.getPrice(), p.isPaid() ? "оплачен" : "не оплачен");
        }

        int pIdx = getIntInput("номер товара: ") - 1;
        if (pIdx < 0 || pIdx >= products.size()) {
            System.out.println("неверный номер товара.");
            return;
        }

        Product selected = products.get(pIdx);
        try {
            boolean success = client.purchase(selected);
            if (success) {
                ArrayList<Product> purchased = new ArrayList<>();
                purchased.add(selected);
                Order newOrder = OrderFactory.createExpressOrder(purchased);
                orderService.addOrder(newOrder);
                System.out.println("Заказ создан и добавлен в историю.");
            }
        } catch (InsufficientFundsException e) {
            System.out.println("ОТКАЗ В ОПЛАТЕ: " + e.getMessage());
        } catch (ProductAlreadyPaidException e) {
            System.out.println("ОТКАЗ В ОПЛАТЕ: " + e.getMessage());
        } catch (ClientBlockedException e) {
            System.out.println("ОТКАЗ В ОПЛАТЕ: " + e.getMessage());
        } catch (InvalidPaymentException e) {
            System.out.println("ОТКАЗ В ОПЛАТЕ: " + e.getMessage());
        } catch (OrderCreationException e) {
            System.out.println("ОШИБКА СОЗДАНИЯ ЗАКАЗА: " + e.getMessage());
        } catch (BaseShopException e) {
            System.out.println("ОШИБКА ПОКУПКИ: " + e.getMessage());
        } finally {
            System.out.println("Операция покупки завершена.");
        }
    }

    private void filterWithLambda() {
        System.out.println("\nФильтрация (lambda)");
        System.out.println("1. Товары дороже 50000");
        System.out.println("2. Только оплаченные");
        System.out.println("3. Только электроника");
        System.out.println("4. Найти первый товар по цене (findFirst)");
        int choice = getIntInput("Выбор: ");

        ProductChecker checker;
        switch (choice) {
            case 1 -> checker = p -> p.getPrice() > 50000;
            case 2 -> checker = p -> p.isPaid();
            case 3 -> checker = p -> p instanceof ProductTypes.Electronic;
            case 4 -> {
                double targetPrice = getDoubleInput("Введите минимальную цену для поиска: ");
                try {
                    repository.findFirstByPrice(targetPrice);
                } catch (com.shop.exception.ProductNotFoundException e) {
                    System.out.println("ОШИБКА ПОИСКА: " + e.getMessage());
                }
                return;
            }
            default -> { System.out.println("Неверный выбор."); return; }
        }

        System.out.println("\nРезультат:");
        boolean found = repository.filterAndShow(checker);
        if (!found) System.out.println("Ничего не найдено.");
    }

    private void streamOperations() {
        System.out.println("\nОперации Stream API\n" +
                "1. Средняя цена\n" +
                "2. Самый дорогой товар\n" +
                "3. Список названий\n" +
                "4. Группировка по типу\n" +
                "5. Подсчёт по статусу оплаты");

        int choice = getIntInput("Выбор: ");

        switch (choice) {
            case 1 -> {
                double avg = repository.getAveragePrice();
                System.out.printf("Средняя цена: %.2f%n", avg);
            }
            case 2 -> repository.showMostExpensive();
            case 3 -> {
                String names = repository.getTitlesAsString();
                System.out.println("Названия: " + names);
            }
            case 4 -> repository.showGroupedByType();
            case 5 -> repository.showCountByStatus();
            default -> System.out.println("Неверный выбор.");
        }
    }
}