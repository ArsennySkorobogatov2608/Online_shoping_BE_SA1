public class Main {
    public static void main(String[] args) {

//        Category category1 = new Category("товар такой то", 50.1, "описание товара");
//        System.out.println(category1.getID() + " " + category1.get_Title() + " "
//                + category1.get_Price() + " " + category1.get_Description());
//
//        Category category2 = new Category("товар вот этот", 50.2, "описание товара");
//        System.out.println(category2.getID() + " " + category2.get_Title() + " "
//                + category2.get_Price() + " " + category2.get_Description());
//
//        Category category3 = new Category("товар вот токй то вот", 50.2, "описание товара");
//        System.out.println(category3.getID() + " " + category3.get_Title()
//                + " " + category3.get_Price() + " " + category3.get_Description());

        Catalog myCatalog = new Catalog();

        myCatalog.ADD_Category("Электроника");
        myCatalog.ADD_Category("Спорт");

        myCatalog.ADD_SubCategory("Смартфоны");
        myCatalog.ADD_SubCategory("Мячи");

        myCatalog.SHOW_Category();
    }
}


//TODO - этап 2:
// 2.1 - в классе Product (добавить) должно быть поле Description - описание;
// Product class должен быть абстрактным
// 2.2 - Category (добавить): 1 - id, 2 - title, 3 - Description, список ArrayList - сохранит в себе список категории
// 2.3 - автогенерация id, каждый новый шаг - новое id которое генерируется самостоятельно
// 2.4 - наследники Electronic, GardenItem
// 2.5 - создаем новый класс: MobileDevice, должен быть наследником наследником электроники
// 2.6 - создаем несколько объектов из новых классов в main, добавляем
// 2.7 - в Category нужно добавить AddCategoty, ShowCategory

//TODO - 3 этап
// 3.1 - нужно создать класс Catalog (уже есть), добавить ADD_Category, SHOW_Categoty
// 3.2 - добавляет счетчик в Catalog: 1 счетчик - сколько есть категорий; 2 счетчик - сколько есть sub Category
// 3.3 -