public enum ClientStatus {
    NEW("Новый"),
    ACTIVE("Активный"),
    BLOCKED("Заблокирован");

    private final String label;
    ClientStatus(String label) { this.label = label; }
    public String getLabel() { return label; }
}