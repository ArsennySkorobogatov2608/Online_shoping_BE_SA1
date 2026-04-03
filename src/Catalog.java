//TODO - 1: name,  2: Title, 3: Price, в этом классе делаем инкпсуляцию, после нужно в main создать 2 объекта,
public class Catalog {
    private   int ID;
    private String Title;
    private double price;

    public Catalog() {}

    public Catalog(int ID, String Title, double price) {
        this.ID = ID;
        this.Title = Title;
        this.price = price;
    }

    public int get_ID() {
        return this.ID;
    }
    public void set_ID(int ID) {
        this.ID = ID;
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
}


