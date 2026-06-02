public class MobileDevice extends Electronic {
    public MobileDevice(String title, double price, String description) {
        super(title, price, description);
    }

    @Override
    public void showInfo() {
        System.out.println("мобильные устройства id: " + getId() +
                "   название: " + getTitle() +
                "   цена: " + getPrice() +
                "   описание: " + getDescription());
    }
}