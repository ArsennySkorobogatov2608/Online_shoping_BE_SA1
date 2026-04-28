public class Client implements Financeable {
    private double balance;
    private int number;
    private String name;

    public Client(int number, String name, double initialBalance) {
        this.number = number;
        this.name = name;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Баланс пополнен на %.2f%n", amount);
        }
    }

    @Override
    public double checkBalance() {
        return balance;
    }

    @Override
    public boolean hasEnoughMoney(double amount) {
        return balance >= amount;
    }

    @Override
    public String getFinancialStatus() {
        return String.format("Клиент #%d (%s) | Баланс: %.2f", number, name, balance);
    }

    public boolean purchase(Product product) {
        if (product.isPaid()) {
            System.out.println("Товар \"" + product.getTitle() + "\" уже оплачен.");
            return false;
        }
        double price = product.getFinalPrice();
        if (hasEnoughMoney(price)) {
            balance -= price;
            product.pay(price);
            System.out.printf("Списание: %.2f. Остаток: %.2f%n", price, balance);
            return true;
        } else {
            System.out.printf("Недостаточно средств для \"%s\". Нужно: %.2f, есть: %.2f%n",
                    product.getTitle(), price, balance);
            return false;
        }
    }
}