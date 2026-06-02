public enum StatusOrder {
    CREATE("Создан"),
    PROCESSING("В обработке"),
    SHIPPED("Отправлен"),
    DELIVERED("Доставлен"),
    CANCELLED("Отменён");

    private final String label;
    StatusOrder(String label) { this.label = label; }
    public String getLabel() { return label; }
}