import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Catalog storeCatalog = new Catalog();
        ArrayList<Product> products = new ArrayList<>();

        storeCatalog.addCategory("Электроника");
        storeCatalog.addSubCategory("Смартфоны");
        storeCatalog.addSubCategory("Ноутбуки");
        storeCatalog.addCategory("Дом и сад");
        storeCatalog.addSubCategory("Инструменты");

        Product phone1 = new MobileDevice("iPhone 15", 90000.0, "Новинка от Apple");
        Product phone2 = new MobileDevice("Samsung S24", 85000.0, "Флагман на Android");
        Product trimmer = new GardenItem("Газонокосилка", 15000.0, "Бензиновая, мощная");
        Product laptop = new Electronic("MacBook Air", 120000.0, "Чип M2, 13 дюймов");

        Collections.addAll(products, phone1, phone2, trimmer, laptop);

        boolean running = true;
        while (running) {
            showMenu();
            int choice = getIntInput("\nвыберите опцию: ");

            switch (choice) {
                case 1 -> storeCatalog.showCatalog();
                case 2 -> Product.showAllProducts();
                case 3 -> sortProducts(products);
                case 4 -> compareProducts(products);
                case 5 -> testPaymentWithClient(products);
                case 6 -> {
                    System.out.println("конец работы.");
                    running = false;
                }
                default -> System.out.println("неверный выбор, попробуйте снова.");
            }

            if (running) {
                System.out.print("\nнажмите Enter для продолжения...");
                scanner.nextLine();
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
                5. тест оплаты (через Клиента)
                6. выход
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

    private static void sortProducts(ArrayList<Product> products) {
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
        ArrayList<Product> sorted = new ArrayList<>(products);
        Collections.sort(sorted, comparator);

        System.out.println("\nотсортировано:");
        for (int i = 0; i < sorted.size(); i++) {
            System.out.print((i + 1) + ". ");
            sorted.get(i).showInfo();
        }
    }

    private static void compareProducts(ArrayList<Product> products) {
        System.out.println("\nСРАВНЕНИЕ ТОВАРОВ");
        System.out.println("доступные товары:");
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

    private static void testPaymentWithClient(ArrayList<Product> products) {
        System.out.println("\nТЕСТ ОПЛАТЫ ЧЕРЕЗ КЛИЕНТА");
        Client client = new Client(101, "Алексей", 0.0);
        System.out.println(client.getFinancialStatus());

        double deposit = getDoubleInput("введите сумму для пополнения баланса: ");
        client.deposit(deposit);
        System.out.println(client.getFinancialStatus());

        System.out.println("\nвыберите товар для покупки:");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.printf("%d. %-20s - %.2f - %s%n",
                    i + 1, p.getTitle(), p.getPrice(), p.isPaid() ? "оплачен" : "не оплачен");
        }

        int pIdx = getIntInput("номер товара: ") - 1;
        if (pIdx >= 0 && pIdx < products.size()) {
            client.purchase(products.get(pIdx));
        } else {
            System.out.println("неверный номер товара.");
        }
    }
}
