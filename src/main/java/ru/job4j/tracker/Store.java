package ru.job4j.tracker;

import java.util.List;

/**
 * Интерфейс хранилища для MemTracker и SqlTracker
 */

public interface Store {
    Item add(Item item);
    boolean replace(int id, Item item);
    boolean delete(int id);
    List<Item> findAll();
    List<Item> findByName(String key);
    Item findById(int id);
}
