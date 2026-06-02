import java.util.List;

public class OrderFactory {
    public static Order createOrder(List<Product> items) {
        return new Order(items);
    }

    public static Order createExpressOrder(List<Product> items) {
        Order order = new Order(items);
        order.setStatus(StatusOrder.PROCESSING);
        return order;
    }
}