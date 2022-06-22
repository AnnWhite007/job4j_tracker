package ru.job4j.tracker;

/**
 * Вывод в консоль мы заменяем интерфейсом Output
 * Зависимости будем делать через конструторы
 */
public interface Output {
    void println(Object obj);
}
