package ru.job4j.tracker;

/**
 * 2. Рефакторинг - Шаблон Декоратор для валидатора.
 * Класс получает данные из системы ввода до тех пор, пока не введут правильные символы
 * Смысл Декоратора - добавление нового поведения в уже существующее поведение.
 * Base интерфейс описывающий поведение.
 * Class класс реализующий поведение.
 * Decor класс реализующий поведение и зависимый от другой реализации Base.
 */

public class ValidateInput implements Input {
    private final Output out;
    /** Создадим поле, которое будет содержать источник данных */
    private final Input in;

    public ValidateInput(Output out, Input input) {
        this.out = out;
        this.in = input;
    }

    @Override
    public String askStr(String question) {
        return in.askStr(question);
    }

    @Override
    public int askInt(String question) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = in.askInt(question);
                invalid = false;
            } catch (NumberFormatException nfe) {
                out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}
