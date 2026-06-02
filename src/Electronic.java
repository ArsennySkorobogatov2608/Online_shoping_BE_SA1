public class Electronic extends Product {
    public Electronic(String title, double price, String description) {
        super(title, price, description);
    }

    @Override
    public void showInfo() {
        System.out.println("электроника id: " + getId() +
                "   название: " + getTitle() +
                "   цена: " + getPrice() +
                "   описание: " + getDescription());
    }
}