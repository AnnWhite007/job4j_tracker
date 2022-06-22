package ru.job4j.tracker;

import java.util.Scanner;

/**
 * 4.1. Разрыв зависимости StartUI от Scanner.
 * Класс, который реализует интерфейс Input и который внутри будет работать с классом Scanner.
 * Если класс зависит от внешних ресурсов (системы ввода-вывода, баз данных, веб-сервисов),
 * то эти зависимости нужно разрывать через введение интерфейса.
 */


public class ConsoleInput implements  Input {
    private Scanner scanner = new Scanner(System.in);


    @Override
    public String askStr(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    @Override
    public int askInt(String question) {
        return Integer.valueOf(askStr(question));
    }
}
