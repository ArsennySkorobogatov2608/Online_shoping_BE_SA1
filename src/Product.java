import java.util.ArrayList;

public abstract class Product extends Category implements Payable {
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
        } else {
            System.out.println("недостаточно средств для " + getTitle());
        }
    }

    @Override
    public boolean isPaid() { return paid; }

    public static void showAllProducts() {
        System.out.println("\nвсе товары");
        for (Product p : allProducts) {
            p.showInfo();
        }
    }

    public boolean canCompareWith(Product other) {
        return other != null && this.getClass().equals(other.getClass());
    }
}