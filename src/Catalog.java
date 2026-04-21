import java.util.ArrayList;

public class Catalog {
    private int categoryCount = 0;
    private int subCategoryCount = 0;
    private ArrayList<ArrayList<Object>> mainCatalog = new ArrayList<>();

    public void addCategory(String name) {
        ArrayList<Object> newCategory = new ArrayList<>();
        newCategory.add(name);
        newCategory.add(new ArrayList<String>());
        mainCatalog.add(newCategory);
        categoryCount++;
    }

    public void addSubCategory(String subName) {
        if (!mainCatalog.isEmpty()) {
            ArrayList<Object> lastCategory = mainCatalog.get(mainCatalog.size() - 1);
            ArrayList<String> subList = (ArrayList<String>) lastCategory.get(1);
            subList.add(subName);
            subCategoryCount++;
        }
    }

    public void showCatalog() {
        System.out.println("\nКАТАЛОГ");
        for (ArrayList<Object> cat : mainCatalog) {
            System.out.println("Категория: " + cat.get(0));
            ArrayList<String> subs = (ArrayList<String>) cat.get(1);
            for (String sub : subs) {
                System.out.println("    Подкатегория: " + sub);
            }
        }
        System.out.println("Всего категорий: " + categoryCount + ", подкатегорий: " + subCategoryCount);
    }
}
