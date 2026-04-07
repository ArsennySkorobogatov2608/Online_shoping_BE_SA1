import java.util.ArrayList;

public class Catalog extends Category {
    private static int categoryCount = 0;
    private static int subCategoryCount = 0;

    private static ArrayList<String> Category = new ArrayList<>();
    private static ArrayList<String> SubCategory = new ArrayList<>();


    @Override
    public void ADD_Category(String name) {
        super.ADD_Category(name);
        Category.add(name);
        categoryCount++;
        System.out.println("Добавлена категория: " + name);
    }

    public void ADD_SubCategory(String subName) {
        SubCategory.add(subName);
        subCategoryCount++;
        System.out.println("Добавлена подкатегория: " + subName);
    }

    @Override
    public void SHOW_Category() {
        System.out.println("\nкаталог: ");

        for(String g : Category) {
            System.out.println("категория: " + g);
            for (String f : SubCategory) {
                System.out.println("подкатегория: " + f);
            }
        }
        System.out.println("Итого категорий: " + categoryCount);
        System.out.println("Итого подкатегорий: " + subCategoryCount);
    }
}