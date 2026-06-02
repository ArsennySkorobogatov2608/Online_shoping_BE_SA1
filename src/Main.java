import java.util.ArrayList;
import java.util.Scanner;


//TODO Привет, пожалуйста, не трогай мой код

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Catalog catalog = Catalog.getInstance();
        ProductRepository productRepository = new ProductRepository();
        OrderService orderService = OrderService.getInstance(productRepository);
        ClientFactory.setSelectionStrategy(
                new VIPClientSelectionStrategy(150000.0, new CardPaymentStrategy())
        );

        catalog.addCategory("Электроника");
        catalog.addSubCategory("Смартфоны");
        catalog.addSubCategory("Ноутбуки");
        catalog.addCategory("Дом и сад");
        catalog.addSubCategory("Инструменты");

        productRepository.add(new MobileDevice("iPhone 15", 90000.0, "Новинка от Apple"));
        productRepository.add(new MobileDevice("Samsung S24", 85000.0, "Флагман на Android"));
        productRepository.add(new GardenItem("Газонокосилка", 15000.0, "Бензиновая, мощная"));
        productRepository.add(new Electronic("MacBook Air", 120000.0, "Чип M2, 13 дюймов"));

        Client client = ClientFactory.createClient(101, "Алексей", 200000.0);

        boolean running = true;
        while (running) {
            showMenu();
            int choice = getIntInput("\nвыберите опцию: ");

            try {
                switch (choice) {
                    case 1 -> catalog.showCatalog();
                    case 2 -> productRepository.showAll();
                    case 3 -> sortProducts(productRepository);
                    case 4 -> compareProducts(productRepository);
                    case 5 -> testPayment(productRepository, client, orderService);
                    case 6 -> filterWithLambda(productRepository);
                    case 7 -> streamOperations(productRepository);
                    case 8 -> orderService.showOrders();
                    case 9 -> client.showHistory();
                    case 10 -> {
                        System.out.println("конец работы.");
                        running = false;
                    }
                    default -> System.out.println("неверный выбор, попробуйте снова.");
                }
            } catch (BaseShopException e) {
                System.out.println("ОШИБКА МАГАЗИНА: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("НЕПРЕДВИДЕННАЯ ОШИБКА: " + e.getMessage());
            } finally {
                if (running) {
                    System.out.print("\nнажмите Enter для продолжения...");
                    scanner.nextLine();
                }
            }
        }
    }

    private static void showMenu() {
        System.out.println("""
                МЕНЮ ОНЛАЙН-МАГАЗИНА
                1. показать каталог
                2. все товары
                3. сортировать товары
                4. сравнить товары
                5. тест оплаты 
                6. фильтрация (lambda)
                7. операции Stream API
                8. просмотр заказов
                9. история транзакций (чеки)
                10. выход
                """);
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("введите целое число!");
            scanner.next();
            System.out.print(prompt);
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("введите число (можно дробное)!");
            scanner.next();
            System.out.print(prompt);
        }
        double val = scanner.nextDouble();
        scanner.nextLine();
        return val;
    }

    private static void sortProducts(IProductRepository repository) {
        System.out.println("""
                \nСОРТИРОВКА
                1. по названию (А-Я)
                2. по названию (Я-А)
                3. по цене (дешёвые -> дорогие)
                4. по цене (дорогие -> дешёвые)
                5. по ID
                """);

        int choice = getIntInput("выбор: ");
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

        System.out.println("\nотсортировано:");
        for (int i = 0; i < sorted.size(); i++) {
            System.out.print((i + 1) + ". ");
            sorted.get(i).showInfo();
        }
    }

    private static void compareProducts(IProductRepository repository) {
        System.out.println("\nсравнение");
        System.out.println("доступные товары:");
        ArrayList<Product> products = repository.getAll();
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.printf("%d. %-20s [%s]%n", i + 1, p.getTitle(), p.getClass().getSimpleName());
        }

        int idx1 = getIntInput("\nпервый товар (номер): ") - 1;
        int idx2 = getIntInput("второй товар (номер): ") - 1;

        if (idx1 < 0 || idx1 >= products.size() || idx2 < 0 || idx2 >= products.size()) {
            System.out.println("неверный номер!");
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
        System.out.println("\nрезультат:");
        System.out.println(p1.getTitle() + " цена: " + p1.getPrice());
        System.out.println(p2.getTitle() + " цена: " + p2.getPrice());

        if (result < 0) System.out.println("- \"" + p1.getTitle() + "\" идёт раньше по алфавиту");
        else if (result > 0) System.out.println("- \"" + p2.getTitle() + "\" идёт раньше по алфавиту");
        else System.out.println("Названия одинаковые");

        System.out.println("\nдополнительно:");
        System.out.println("equals(): " + p1.equals(p2));
        System.out.println("hashCode: " + p1.hashCode() + " и " + p2.hashCode());
    }

    private static void testPayment(IProductRepository repository, Client client, OrderService orderService) {
        System.out.println("\nтест оплаты");
        System.out.println(client.getFinancialStatus());
        System.out.println("текущий баланс: " + client.checkBalance());

        System.out.println("\nвыберите способ оплаты:");
        System.out.println("1. Банковская карта");
        System.out.println("2. Наличные");
        int strategyChoice = getIntInput("выбор: ");
        client.setStrategy(strategyChoice == 1 ? new CardPaymentStrategy() : new CashPaymentStrategy());

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

    private static void filterWithLambda(IProductRepository repository) {
        System.out.println("\nфильтрация (lambda)");
        System.out.println("1. товары дороже 50000");
        System.out.println("2. только оплаченные");
        System.out.println("3. только электроника");
        System.out.println("4. найти первый товар по цене (findFirst)");
        int choice = getIntInput("выбор: ");

        ProductChecker checker;
        switch (choice) {
            case 1 -> checker = p -> p.getPrice() > 50000;
            case 2 -> checker = p -> p.isPaid();
            case 3 -> checker = p -> p instanceof Electronic;
            case 4 -> {
                double targetPrice = getDoubleInput("введите минимальную цену для поиска: ");
                try {
                    repository.findFirstByPrice(targetPrice);
                } catch (ProductNotFoundException e) {
                    System.out.println("ОШИБКА ПОИСКА: " + e.getMessage());
                }
                return;
            }
            default -> { System.out.println("неверный выбор."); return; }
        }

        System.out.println("\nрезультат:");
        boolean found = repository.filterAndShow(checker);
        if (!found) System.out.println("ничего не найдено.");
    }

    private static void streamOperations(IProductRepository repository) {
        System.out.println("\nоперации Stream API\n" +
                "1. средняя цена\n" +
                "2. самый дорогой товар\n" +
                "3. список названий\n" +
                "4. группировка по типу\n" +
                "5. подсчёт по статусу оплаты");

        int choice = getIntInput("выбор: ");

        switch (choice) {
            case 1 -> {
                double avg = repository.getAveragePrice();
                System.out.printf("средняя цена: %.2f%n", avg);
            }
            case 2 -> repository.showMostExpensive();
            case 3 -> {
                String names = repository.getTitlesAsString();
                System.out.println("названия: " + names);
            }
            case 4 -> repository.showGroupedByType();
            case 5 -> repository.showCountByStatus();
            default -> System.out.println("неверный выбор.");
        }
    }
}