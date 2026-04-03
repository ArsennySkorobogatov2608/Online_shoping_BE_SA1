public class Main {
    public static void main(String[] args) {

        Catalog catalog1 = new Catalog(1, "помидорры", 100.3);
        System.out.println(catalog1.get_ID() + " " + catalog1.get_Title() + " " + catalog1.get_Price());

        Catalog catalog2 = new Catalog(2, "морковь", 50.0);
        System.out.println(catalog2.get_ID() + " " + catalog2.get_Title() + " " + catalog2.get_Price());

    }
}

