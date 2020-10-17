package ru.job4j.tracker;

import java.time.LocalDateTime;

/**
 * 2.2. Перегрузка конструктора
 * Конструктор - это специальный метод,
 * который вызывается при создании нового объекта.
 * Конструктор инициализирует объект непосредственно во время создания.
 * Имя конструктора совпадает с именем класса, включая регистр,
 * а по синтаксису конструктор похож на метод без возвращаемого значения.
 * В отличие от метода, конструктор никогда ничего не возвращает.
 * В каждом классе создается по умолчанию конструктор без параметров.
 * Если мы явно создаем любой другой конструктор в классе,
 * то конструктор по умолчанию становится не доступным.
 * Перегрузка конструкторов - это механизм,
 * позволяющий создавать конструкторы с разными входными параметрами.
 * public class Item {
 *     public Item() { - (конструктор без параметра)
 *     }
 *     public Item(String name) {
 *     }
 *
 *     2.7. toString
 *     Метод toString() служит, чтобы код можно было переиспользовать и не думать о том,
 *     как выводить строковое представление объектов,
 *     определен в классе  Object, наследниками которого по умолчанию являются все классы в Java.
 *     Для того чтобы этот метод использовать в своих классах,
 *     необходимо использовать механизм переопределения.
 */

public class Item {
    private int id;
    private String name;
    private LocalDateTime created = LocalDateTime.now();

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

    public LocalDateTime getCreated() {
        return created;
    }

    public Item() {
    }
    public Item(int id){
        this.id = id;
    }
    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}