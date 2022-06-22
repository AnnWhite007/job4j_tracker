package ru.job4j.tracker;

/**
 * 5. Input и полиморфизм.
 * объект StubInput, в котором мы можем запрограммировать ввод от пользователя
 * метод askStr возвращает параметры, которые мы хотим
 */

public class StubInput implements Input {
    /** Массив строк */
    private String[] answers;
    /** Счетчик, чтобы при повторном вызове метода askStr мы получали следующую ячейку из нашего массива */
    private int position = 0;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    @Override
    public String askStr(String question) {
        return answers[position++];
    }

    @Override
    public int askInt(String question) {
        return Integer.parseInt(askStr(question));
    }
}
