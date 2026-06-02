import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ImmutableReceipt {
    private final String clientName;
    private final String productName;
    private final double amount;
    private final LocalDateTime transactionTime;
    private final String paymentMethod;
    private final boolean success;
    private final String failureReason;

    public ImmutableReceipt(String clientName, String productName, double amount, String paymentMethod, boolean success, String failureReason) {
        this.clientName = clientName;
        this.productName = productName;
        this.amount = amount;
        this.transactionTime = LocalDateTime.now();
        this.paymentMethod = paymentMethod;
        this.success = success;
        this.failureReason = failureReason;
    }

    public ImmutableReceipt(String clientName, String productName, double amount, String paymentMethod) {
        this(clientName, productName, amount, paymentMethod, true, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public double getAmount() {
        return amount;
    }

    public void printReceipt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM HH:mm");
        String status = success ? "ОПЛАЧЕНО" : "ОТКАЗАНО";

        System.out.printf("ЧЕК [%s] | Кл: %-12.12s | Тов: %-18.18s | %9.2f ₽ | %s | %s",
                status,
                truncate(clientName, 12),
                truncate(productName, 18),
                amount,
                transactionTime.format(formatter),
                truncate(paymentMethod, 15));

        if (!success && failureReason != null) {
            System.out.printf("%n       - по причине: %s", truncate(failureReason, 70));
        }
        System.out.println();
    }

    private String truncate(String text, int maxLength) {
        if (text == null) return "";
        if (text.length() <= maxLength) return text;
        return text.substring(0, maxLength - 2) + "..";
    }
}