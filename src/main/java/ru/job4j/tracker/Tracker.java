package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 5. Tracker - хранилище.
 * 6. Метод замены заявки. Tracker.replace
 * 7. Метод удаления заявки Tracker.delete
 * Метод public Item add(Item item) добавляет заявку,
 * переданную в аргументах в массив заявок items.
 * Метод public Item findById(int id) проверяет в цикле все элементы массива items,
 * равнивая id с аргументом int id
 * и возвращает найденный Item. Если Item не найден - возвращает null.
 * Метод public Item[] findAll() возвращает копию массива
 * items без null элементов (без пустых ячеек)
 * Метод public Item[] findByName(String key) проверяет в цикле все элементы массива items,
 * сравнивая name (используя метод getName класса Item) с аргументом метода String key.
 * Элементы, у которых совпадает name, копирует в результирующий массив и возвращает его.
 * Алгоритм этого метода аналогичен методу findAll.
 * replace - метод замены заявки. То есть удалить заявку,
 * которая уже есть в системе и добавить в эту ячейку новую.
 * delete - метод удаления заявки (запись в ячейку нулевой ссылки - null),
 * c перемещением всех ячеек справа на одну позицию с помощью метода
 * System.arraycopy(source, startPos, dist, distPos, length);
 * source - массив откуда нужно скопировать элементы начиная с позиции startPos и до позиции startPos + length.
 * length - сколько элементов взять начиная от startPos.
 * dist - массив, куда вставить скопированные элементы от source.
 * Этот метод может работать с одним массивом для source и dist.
 * distPos - начиная с какого элемента вставлять скопированные ячейки.
 *
 *  8. Что такое валидация?
 * - это проверка параметров метода. Если параметры не верные, то выполнить метод нельзя.
 */

public class Tracker {
    private final List<Item> items = new ArrayList<>();
    /** Поле для генерации нового ключа, представляет собой последовательность. */
    private  int ids = 1;

    /** Добавление заявок */
    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    /** Получение заявки по id, возвращает объект Item.
     * Если индекс найден возвращаем item, иначе null. */
    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    /** Получение списка всех заявок.
     * @return возвращаем копию списка */
    public List<Item> findAll() {
        return List.copyOf(items);
    }

    /** Получение списка по имени. */
    public List<Item> findByName(String key) {
        List<Item> sameName = new ArrayList<>();
        for (Item value : items) {
            if (value.getName().equals(key)) {
                sameName.add(value);
            }
        }
        return sameName;
    }

    /** Метод, который будет возвращать index по id. */
    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    /** Замена заявки + валидация. */
    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            item.setId(id);
            items.set(index, item);
        }
        return rsl;
    }
    /** Удаление элемента из массива и перемещение всех ячеек справа на одну позицию + валидация
     * Элементы, следующие после удалённого элемента, сдвигаются влево, а размер списочного массива уменьшается на единицу */
    public boolean delete(int id) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            items.remove(index);
        }
        return rsl;
    }

    public List<Item> sorted(List<Item> forSort) {
        Collections.sort(forSort, new SortBy());
        return forSort;
    }

    public List<Item> sortedReverse(List<Item> forSort) {
        sorted(forSort);
        Collections.reverse(forSort);
        return forSort;
    }
}