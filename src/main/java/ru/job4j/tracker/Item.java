package ru.job4j.tracker;

/**
 * Класс Item описывает модель заявления.
 * Поле id - это уникальный номер заявления.
 * Поле name содержит название заявления.
 *
 *  2.7. toString
 *  *     Метод toString() служит, чтобы код можно было переиспользовать и не думать о том,
 *  *     как выводить строковое представление объектов,
 *  *     определен в классе  Object, наследниками которого по умолчанию являются все классы в Java.
 *  *     Для того чтобы этот метод использовать в своих классах,
 *  *     необходимо использовать механизм переопределения.
*/
public class Item {

    private int id;
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id: " + id + ", name: " + name;
    }
}
