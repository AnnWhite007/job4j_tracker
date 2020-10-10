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
    }
    public Item(int id, String name) {
    }
}