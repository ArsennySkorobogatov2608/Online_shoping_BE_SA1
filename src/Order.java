import java.util.List;

public class Order {
    private static int autoId = 0;
    private int id;
    private List<Product> items;
    private StatusOrder status;

    public Order(List<Product> items) {
        this.id = ++autoId;
        this.items = items;
        this.status = StatusOrder.CREATE;
    }

    public void setStatus(StatusOrder status) { this.status = status; }
    public StatusOrder getStatus() { return status; }
    public double getTotal() { return items.stream().mapToDouble(Product::getFinalPrice).sum(); }

    public void showInfo() {
        System.out.printf("заказ #%d | статус: %-12s | сумма: %10.2f | товаров: %d%n",
                id, status.getLabel(), getTotal(), items.size());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status.getLabel() +
                ", total=" + getTotal() +
                ", itemsCount=" + items.size() +
                '}';
    }
}