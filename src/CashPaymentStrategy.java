public class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        return true;
    }

    @Override
    public String getStrategyName() {
        return "Наличные";
    }
}