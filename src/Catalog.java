import java.util.ArrayList;

public class Catalog {
    private int categoryCount = 0;
    private int subCategoryCount = 0;

    private static class CategoryEntry {
        String categoryName;
        ArrayList<String> subCategories;

        CategoryEntry(String name) {
            this.categoryName = name;
            this.subCategories = new ArrayList<>();
        }
    }

    private ArrayList<CategoryEntry> mainCatalog = new ArrayList<>();

    public void addCategory(String name) {
        mainCatalog.add(new CategoryEntry(name));
        categoryCount++;
    }

    public void addSubCategory(String subName) {
        if (!mainCatalog.isEmpty()) {
            CategoryEntry last = mainCatalog.get(mainCatalog.size() - 1);
            last.subCategories.add(subName);
            subCategoryCount++;
        }
    }

    public void showCatalog() {
        System.out.println("\nкаталог");
        for (CategoryEntry cat : mainCatalog) {
            System.out.println("категория: " + cat.categoryName);
            for (String sub : cat.subCategories) {
                System.out.println("   - подкатегория: " + sub);
            }
        }
        System.out.println("всего: категорий=" + categoryCount +
                ", подкатегорий=" + subCategoryCount);
    }
}