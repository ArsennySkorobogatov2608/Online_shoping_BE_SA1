public class Main {
    public static void main(String[] args) {
        Catalog storeCatalog = new Catalog();

        storeCatalog.addCategory("Электроника");
        storeCatalog.addSubCategory("Смартфоны");
        storeCatalog.addSubCategory("Ноутбуки");

        storeCatalog.addCategory("Дом и сад");
        storeCatalog.addSubCategory("Инструменты");

        storeCatalog.showCatalog();

        System.out.println("\nсоздание товаров");

        Product phone = new MobileDevice("iPhone 15", 90000.0, "Новинка от Apple");
        Product trimmer = new GardenItem("Газонокосилка", 15000.0, "Мощная, бензиновая");
        Electronic laptop = new Electronic("MacBook Air", 120000.0, "На чипе M2");

        Product.showAllProducts();


        System.out.println("\nпроверка оплаты");
        System.out.println("Статус оплаты до: " + phone.isPaid());
        phone.pay(90000.0);
        System.out.println("Статус оплаты после: " + phone.isPaid());

        Product phone2 = new MobileDevice("iPhone 15", 90000.0, "Новинка от Apple");
        System.out.println("\nСравнение одинаковых товаров: " + phone.equals(phone2));

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

//TODO - этап 4 (полиморфизм)
// 4.1 - надо в Category добавить абстрактный метод Show info, который мы будем переобразовывать
// 4.2 - создать несколько разных товаров в Product в списке в виде Array list
// 4.3 - надо в show info что бы весть список показать на экране
// 4.4 -


//TODO 5.1 - 2 новых интерфейса
// Payble - помогатеот объектам товаров
//  1- возвращает double - GetFinalPrice
//  2- void - pay с параметром double(amount)
//  3- bool - isPaid
// Financible - помогате объектам клиентам
//  1- double - ChekBalance (без параметра)
//  2- bool - HasEnouthMoney (double emouth)
//  3-String - GetFinalsesStatus (без параметра)
// 5.2 - необходимо все классы расширять при помощи Hashcode, equals, instanceof и подобного
// 5.3 - сравнивать объекты по цвету, названию и.т.д