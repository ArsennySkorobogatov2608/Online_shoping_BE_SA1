public class DataInitializer {

    public static Client seedData(Catalog catalog, ProductRepository repository) {
        ClientFactory.setSelectionStrategy(
                new VIPClientSelectionStrategy(150000.0, new CardPaymentStrategy())
        );

        catalog.addCategory("Электроника");
        catalog.addSubCategory("Смартфоны");
        catalog.addSubCategory("Ноутбуки");
        catalog.addCategory("Дом и сад");
        catalog.addSubCategory("Инструменты");


        repository.add(new MobileDevice("iPhone 15", 90000.0, "Новинка от Apple"));
        repository.add(new MobileDevice("Samsung S24", 85000.0, "Флагман на Android"));
        repository.add(new GardenItem("Газонокосилка", 15000.0, "Бензиновая, мощная"));
        repository.add(new Electronic("MacBook Air", 120000.0, "Чип M2, 13 дюймов"));

        return ClientFactory.createClient(101, "Алексей", 200000.0);
    }
}