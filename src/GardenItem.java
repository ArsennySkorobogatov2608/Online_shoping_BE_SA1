public class GardenItem extends Product {
    public GardenItem(String title, double price, String description) {
        super(title, price, description);
    }

    @Override
    public void showInfo() {
        System.out.println("[дом и сад] id: " + getId() +
                " | название: " + getTitle() +
                " | цена: " + getPrice() +
                " | описание: " + getDescription());
    }
}