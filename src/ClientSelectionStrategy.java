public interface ClientSelectionStrategy {
    Client selectClient(int id, String name, double balance);
}