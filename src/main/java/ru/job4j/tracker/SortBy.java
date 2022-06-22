package ru.job4j.tracker;

import java.util.Comparator;

/**
 * 0. Сортировка
 * может быть двух видов: по возрастанию и по убыванию.
 * Отсортировать можно не только числительные типы, но так же символьные типы.
 * В Java большинство коллекций поддерживает метод Collections.sort, который позволяет отсортировать коллекцию.
 * Через интерфейс java.util.Comparable мы можем задать только один порядок сортировки. Чтобы сделать другой порядок,
 * в Java есть интерфейс, который не привязан к модели данных. Это интерфейс java.util.Comparator.
 */

public class SortBy implements Comparator<Item> {

    @Override
    public int compare(Item name1, Item name2) {
        return name1.getName().compareTo(name2.getName());
    }
}