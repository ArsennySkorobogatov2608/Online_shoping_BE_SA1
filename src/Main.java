public class Main {

    //TODO привет, пожалуйста не трогай мой код

    public static void main(String[] args) {
        Catalog catalog = Catalog.getInstance();
        ProductRepository productRepository = new ProductRepository();
        OrderService orderService = OrderService.getInstance(productRepository);

        Client demoClient = DataInitializer.seedData(catalog, productRepository);

        ShopApplication app = new ShopApplication(productRepository, orderService, demoClient);
        app.run();
    }
}