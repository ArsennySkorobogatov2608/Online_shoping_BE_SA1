public class GardenItem extends Product{
    // пока не трогаем

    public GardenItem (String газонокосилка, double v, String s) {}

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
