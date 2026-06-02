import java.util.ArrayList;
import java.util.List;

public abstract class Category {
    private static int autoId = 0;
    private int id;
    private String title;
    private String description;
    private final List<String> subCategories = new ArrayList<>();

    public Category() {
        this.id = ++autoId;
    }

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

    public void addSubCategory(String subName) {
        subCategories.add(subName);
    }

    public void showSubCategories() {
        System.out.println("подкатегории: " + subCategories);
    }
}
