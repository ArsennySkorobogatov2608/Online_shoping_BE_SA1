import java.util.ArrayList;

public class Catalog extends Category {
    private static int categoryCount = 0;
    private static int subCategoryCount = 0;

    private static ArrayList<ArrayList<Object>> mainCatalog = new ArrayList<>();

    @Override
    public void ADD_Category(String name) {
        ArrayList<Object> newCategory = new ArrayList<>();
        newCategory.add(name);
        newCategory.add(new ArrayList<String>());

        mainCatalog.add(newCategory);
        categoryCount++;
        System.out.println("добавлена категория: " + name);
    }

    public void ADD_SubCategory(String subName) {
        if (!mainCatalog.isEmpty()) {
            ArrayList<Object> lastCategory = mainCatalog.get(mainCatalog.size() - 1);
            ArrayList<String> subList = (ArrayList<String>) lastCategory.get(1);

            subList.add(subName);
            subCategoryCount++;
            System.out.println("добавлена подкатегория: " + subName + " в " + lastCategory.get(0));
        }
    }

    @Override
    public void SHOW_Category() {
        System.out.println("\nвесь каталог");

        for (ArrayList<Object> cat : mainCatalog) {
            String catName = (String) cat.get(0);
            ArrayList<String> subs = (ArrayList<String>) cat.get(1);

            System.out.println("категория: " + catName);
            for (String sub : subs) {
                System.out.println("подкатегория: " + sub);
            }
        }

        System.out.println("всего категорий: " + categoryCount);
        System.out.println("всего подкатегорий: " + subCategoryCount);
    }
}


// все категории = [
// [категория, [подкатегория, [подподкатегория, подподкатегория]]],
// [категория, [подкатегория, [подподкатегория, подподкатегория]]],
// [категория, [подкатегория, [подподкатегория, подподкатегория]]],
// ]