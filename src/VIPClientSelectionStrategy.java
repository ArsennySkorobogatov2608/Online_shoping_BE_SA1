public class VIPClientSelectionStrategy implements ClientSelectionStrategy {
    private final double vipThreshold;
    private final PaymentStrategy defaultStrategy;

    public VIPClientSelectionStrategy(double vipThreshold, PaymentStrategy defaultStrategy) {
        this.vipThreshold = vipThreshold;
        this.defaultStrategy = defaultStrategy;
    }

    @Override
    public Client selectClient(int id, String name, double balance) {
        Client client = new Client(id, name, balance);
        if (balance >= vipThreshold) {
            client.setStrategy(defaultStrategy);
            client.setStatus(ClientStatus.ACTIVE);
        }
        return client;
    }
}