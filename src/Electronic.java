public class Electronic extends Product {
    public Electronic(String title, double price, String description) {
        super(title, price, description);
    }

    Electronic() {
    }

    @Override
    public void showInfo() {
        System.out.println("[Электроника] ID: " + getId() + " | Название: " + getTitle() + " | Цена: " + getPrice());
    }
    @Override
    public double GetFinalPrice() {
        return 0;
    }
    @Override
    public void Pay(double amount) {
    }
    @Override
    public boolean IsPaid() {
        return false;
    }
}