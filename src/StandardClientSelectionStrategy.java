public class StandardClientSelectionStrategy implements ClientSelectionStrategy {
    private final PaymentStrategy defaultStrategy;

    public StandardClientSelectionStrategy(PaymentStrategy defaultStrategy) {
        this.defaultStrategy = defaultStrategy;
    }

    @Override
    public Client selectClient(int id, String name, double balance) {
        Client client = new Client(id, name, balance);
        client.setStrategy(defaultStrategy);
        return client;
    }
}