import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static OrderService instance;
    private final List<Order> orders = new ArrayList<>();
    private final IProductRepository productRepository;

    private OrderService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static OrderService getInstance(IProductRepository productRepository) {
        if (instance == null) {
            instance = new OrderService(productRepository);
        }
        return instance;
    }

    public void addOrder(Order order) throws OrderCreationException {
        if (order == null) {
            throw new OrderCreationException("Нельзя добавить пустой заказ.");
        }
        if (order.getTotal() <= 0) {
            throw new OrderCreationException("Сумма заказа должна быть больше нуля.");
        }
        orders.add(order);
    }

    public void showOrders() {
        System.out.println("\nистория заказов:");
        if (orders.isEmpty()) {
            System.out.println("заказов нет.");
            return;
        }
        orders.forEach(Order::showInfo);
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }
}