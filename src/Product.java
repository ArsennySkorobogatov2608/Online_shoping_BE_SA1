import java.util.ArrayList;

abstract class Product extends Category {
    private static int auto_ID = 0;
    private int id;
    private String Title;
    private double price;
    private String Description;

    ArrayList<String> product = new ArrayList<>();
    ArrayList<String> sub_product = new ArrayList<>();

    public Product(String Title, double price, String Description) {
        auto_ID++;
        this.id = auto_ID;
        this.Title = Title;
        this.price = price;
        this.Description = Description;
    }

    @Override
    public int getID() {
        return this.id;
    }
    @Override
    public void set_ID(int id) {
        this.id = id;
    }

    @Override
    public String get_Title() {
        return this.Title;
    }
    @Override
    public void set_Title(String Title) {
        this.Title = Title;
    }

    @Override
    public double get_Price() {
        return this.price;
    }
    @Override
    public void set_Price(double price) {
        this.price = price;

    }

    @Override
    public String get_Description() {
        return this.Description;
    }
    @Override
    public void set_Description(String Description) {
        this.Description = Description;
    }


    public void ShowInfo() {
        for (String i : product) {
            System.out.println(i);
        }
    }

    public void Add_Product() {
        sub_product.add(String.valueOf(id));
        sub_product.add(Title);
        sub_product.add(String.valueOf(price));
        sub_product.add(Description);
    }
}
