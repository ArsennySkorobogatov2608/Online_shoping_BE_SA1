import java.util.ArrayList;

abstract class Product extends Category implements Payable {
    private double price;
    private boolean paid = false;

    protected static ArrayList<Product> allProducts = new ArrayList<>();

    public Product() {}

    public Product(String title, double price, String description) {
        super(title, description);
        this.price = price;
        allProducts.add(this);
    }

    public double getPrice() { return price; }

    @Override
    public double getFinalPrice() { return price; }

    @Override
    public void pay(double amount) {
        if (amount >= price) {
            paid = true;
            System.out.println(getTitle() + " оплачен.");
        }
    }

    @Override
    public boolean isPaid() { return paid; }

    // Этап 4.3: Общий вывод всех продуктов
    public static void showAllProducts() {
        System.out.println("Список всех товаров");
        for (Product p : allProducts) {
            p.showInfo();
        }
    }
}
