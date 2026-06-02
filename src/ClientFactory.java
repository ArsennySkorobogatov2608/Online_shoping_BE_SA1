import java.util.List;

public class ClientFactory {
    private static ClientSelectionStrategy selectionStrategy;

    public static void setSelectionStrategy(ClientSelectionStrategy strategy) {
        selectionStrategy = strategy;
    }

    public static Client createClient(int id, String name, double balance) {
        if (selectionStrategy != null) {
            return selectionStrategy.selectClient(id, name, balance);
        }
        return new Client(id, name, balance);
    }

    public static Client createStandardClient(int id, String name, double balance) {
        return new Client(id, name, balance);
    }

    public static Client createVIPClient(int id, String name, double balance, PaymentStrategy strategy) {
        Client client = new Client(id, name, balance);
        client.setStrategy(strategy);
        client.setStatus(ClientStatus.ACTIVE);
        return client;
    }
}