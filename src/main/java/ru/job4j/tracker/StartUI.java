package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * 2.1. Реализация класса StartUI
 * Консольное приложение для работы с классом ru.job4j.tracker.MemTracker.
 * Вместо вызова scanner.nextLine() - input.askStr(msg), где msg - это сообщение,
 * которое вы хотели бы вывести пользователю перед его вводом
 * 4.2. Статические методы.
 * - это методы привязанные к классу, а не к объекту (static).
 * разбиваем код на функциональные блоки
 * 8. Реализация меню за счет шаблона стратегия.
 * Если мы разорвем зависимость от конкретных действия в StartUI,
 * то мы сможем добавлять новые действия без изменения существующего кода.
 * В StartUI нам нужен массив действий.
 * Каждая ячейка у нас будет одно действие.
 * Такая структура описывает наше меню.
 */
public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    /**
     * Вывод меню.
     * Получаем от пользователя пункт меню, этот параметр мы используем в качестве индекса в массиве actions.
     * Проверка, есть ли такой пункт в меню.
     * Получение данных из списка.
     * У полученного объекта вызываем метод execute с передачей параметров input и tracker.
     */
    public void init(Input input, Store store, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, store);
        }
    }

    /**
     * Отвечает за вывод пунктов меню
     */
    private void showMenu(List<UserAction> actions) {
        out.println("Menu.");
        int index = 0;
        for (UserAction value : actions) {
            out.println(index + ". " + value.name());
            index++;
        }
    }

    /**
     * При запуске пользователю отображается меню в консоли.
     * Создаем список с действиями.
     */
    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        try (SqlTracker tracker = new SqlTracker()) {
            tracker.init();
            List<UserAction> actions = List.of(
                    new CreateAction(output),
                    new ShowAllAction(output),
                    new ReplaceItemAction(output),
                    new DeleteItemAction(output),
                    new FindItemByIdAction(output),
                    new FindItemByNameAction(output),
                    new ExitAction()
            );
            new StartUI(output).init(input, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}