import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {

    public enum SortBy { TITLE, PRICE, ID }

    private SortBy sortBy;
    private boolean ascending;

    public ProductComparator(SortBy sortBy, boolean ascending) {
        this.sortBy = sortBy;
        this.ascending = ascending;
    }

    @Override
    public int compare(Product p1, Product p2) {
        if (p1 == null && p2 == null) return 0;
        if (p1 == null) return -1;
        if (p2 == null) return 1;

        int result;
        if (sortBy == SortBy.TITLE) {
            result = p1.getTitle().compareToIgnoreCase(p2.getTitle());
        } else if (sortBy == SortBy.PRICE) {
            result = Double.compare(p1.getFinalPrice(), p2.getFinalPrice());
        } else {
            result = Integer.compare(p1.getId(), p2.getId());
        }

        return ascending ? result : -result;
    }
}