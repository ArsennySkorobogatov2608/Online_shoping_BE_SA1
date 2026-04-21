public interface Payable {

    public double GetFinalPrice();
    public void Pay(double amount);
    public boolean IsPaid();

    void pay(double amount);

    boolean isPaid();
}
