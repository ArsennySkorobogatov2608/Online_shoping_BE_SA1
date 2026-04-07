import java.util.ArrayList;

public class Category {
    private static int auto_ID = 0;
    private int id;
    private String Title;
    private double price;
    private String Description;

    private static ArrayList<String> ADD_SHOW_Category = new ArrayList<>();

    public Category() {}

    public Category(String Title, double price, String Description) {
        auto_ID++;
        this.id = auto_ID;
        this.Title = Title;
        this.price = price;
        this.Description = Description;
    }

    public int getID() {
        return this.id;
    }
    public void set_ID(int id) {
        this.id = id;
    }

    public String get_Title() {
        return this.Title;
    }
    public void set_Title(String Title) {
        this.Title = Title;
    }

    public double get_Price() {
        return this.price;
    }
    public void set_Price(double price) {
        this.price = price;
    }

    public String get_Description() {
        return this.Description;
    }
    public void set_Description(String Description) {
        this.Description = Description;
    }


    public void ADD_Category(String categoryName) {
        ADD_SHOW_Category.add(categoryName);
    }

    public void SHOW_Category() {
        System.out.println("Список категорий:");
        for (String item : ADD_SHOW_Category) {
            System.out.println("- " + item);
        }
    }
}
