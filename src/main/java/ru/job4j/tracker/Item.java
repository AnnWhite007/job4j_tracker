package ru.job4j.tracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Класс Item описывает модель заявления.
 * Поле id - это уникальный номер заявления.
 * Поле name содержит название заявления.
 *
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
 * 2.6. Date. Отображение даты.
 * Работа с датами с помощью классов и методов, которые находятся в пакете java.time,
 * поэтому чтобы их использовать необходимо импортировать этот пакет.
 * LocalDate, LocalTime, LocalDateTime
 * Метод now() - показывает текущее значение
 * DateTimeFormatter - класс который с помощью определенных в нем методов
 * позволяет отформатировать дату в соответствии с образцом
 * Полный перечень принятых сокращений можно найти в документации:
 * "https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html"
 * 2.7. toString
 * Метод toString() служит, чтобы код можно было переиспользовать и не думать о том,
 * как выводить строковое представление объектов,
 * определен в классе  Object, наследниками которого по умолчанию являются все классы в Java.
 * Для того чтобы этот метод использовать в своих классах,
 * необходимо использовать механизм переопределения.
 *
*/
public class Item {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private int id;
    private String name;
    private LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.MICROS);

    public Item(String name) {
        this.name = name;
    }

    public Item(int id, String name, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.created = created;
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return String.format("id: %s, name: %s, created: %s", id, name, FORMATTER.format(created));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
