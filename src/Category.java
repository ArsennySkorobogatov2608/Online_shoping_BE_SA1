import java.util.ArrayList;

public class Category {
    private static int auto_ID = 0;
    private int id;
    private String Title;
    private double price;
    private String Description;
    private String ADD_SHOW;

    public Category() {}

    public Category(String Title, double price, String Description) {
        auto_ID++;
        this.id = auto_ID;
        this.Title = Title;
        this.price = price;
        this.Description = Description;
    }

    public Category(String ADD_SHOW) {
        this.ADD_SHOW = ADD_SHOW;
    }

    public int getID() {
        return this.id;
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

    ArrayList<String> ADD_SHOW_Category = new ArrayList<>();

    public void ADD_Category(String ADD_SHOW) {
        this.ADD_SHOW = ADD_SHOW;
        ADD_SHOW_Category.add(ADD_SHOW);
    }

    public String SHOW_Category() {
        for (String i : ADD_SHOW_Category) {
            System.out.println(i);
        }
        return this.ADD_SHOW;
    }

}
