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

    @Override
    public boolean canCompareWith(Product other) {
        return other instanceof MobileDevice;
    }
}