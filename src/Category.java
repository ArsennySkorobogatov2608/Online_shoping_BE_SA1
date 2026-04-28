import java.util.ArrayList;
import java.util.Objects;

public abstract class Category implements Comparable<Category> {
    private static int autoId = 0;
    private int id;
    private String title;
    private String description;
    protected static ArrayList<String> categoryList = new ArrayList<>();

    public Category() {}

    public Category(String title, String description) {
        this.id = ++autoId;
        this.title = title;
        this.description = description;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }

    public abstract void showInfo();
    public abstract double getFinalPrice();

    public void addCategory(String categoryName) {
        categoryList.add(categoryName);
    }

    public void showCategories() {
        System.out.println("категории: " + categoryList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(title, category.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public int compareTo(Category other) {
        if (other == null) return 1;
        return this.title.compareToIgnoreCase(other.title);
    }
}