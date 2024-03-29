package ru.job4j.tracker;

import java.util.List;

public class ShowAllAction implements UserAction {
    private final Output out;

    public ShowAllAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Show all items";
    }

    @Override
    public boolean execute(Input input, Store store) {
        List<Item> list = store.findAll();
        for (Item value : list) {
            out.println(value);
        }
            return true;
    }
}
