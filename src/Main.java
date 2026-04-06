public class Main {
    public static void main(String[] args) {

        Category category1 = new Category("иуиу", 50.1, "описание 1");
        System.out.println(category1.getID() + " " + category1.get_Title() + " " + category1.get_Price() + " " + category1.get_Description());

        Category category2 = new Category("иуиу", 50.2, "описание 2");
        System.out.println(category2.getID() + " " + category2.get_Title() + " " + category2.get_Price() + " " + category2.get_Description());

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