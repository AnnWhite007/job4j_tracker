package ru.job4j.tracker;

import java.time.format.DateTimeFormatter;

/**
 * 2.6. Date. Отображение даты.
 * Работа с датами с помощью классов и методов, которые находятся в пакете java.time,
 * поэтому чтобы их использовать необходимо импортировать этот пакет.
 * LocalDate, LocalTime, LocalDateTime
 * Метод now() - показывает текущее значение
 * DateTimeFormatter - класс который с помощью определенных в нем методов
 * позволяет отформатировать дату в соответствии с образцом
 * Полный перечень принятых сокращений можно найти в документации:
 * https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
 */

public class StartUI {

    public static void main(String[] args) {
        Item item = new Item( 5, "Anna");;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
        System.out.println(item.getCreated().format(formatter));
        System.out.println("User: id "
                + item.getId() + " , name: "
                + item.getName()
        );
    }
}
