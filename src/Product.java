import java.util.Objects;

public abstract class Product extends Category implements Payable, Comparable<Product> {
    private double price;
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    public Product() {
        super();
    }

    public Product(String title, double price, String description) {
        super(title, description);
        this.price = price;
    }

    public double getPrice() { return price; }

    @Override
    public double getFinalPrice() { return price; }

    @Override
    public void pay(double amount) {
        if (amount >= price) {
            paymentStatus = PaymentStatus.PAID;
        }
    }

    @Override
    public boolean isPaid() { return paymentStatus == PaymentStatus.PAID; }

    public PaymentStatus getPaymentStatus() { return paymentStatus; }

    public boolean canCompareWith(Product other) {
        return other != null && this.getClass() == other.getClass();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getTitle(), product.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }

    @Override
    public int compareTo(Product other) {
        if (other == null) return 1;
        return this.getTitle().compareToIgnoreCase(other.getTitle());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", price=" + price +
                ", status=" + paymentStatus.getLabel() +
                '}';
    }
}