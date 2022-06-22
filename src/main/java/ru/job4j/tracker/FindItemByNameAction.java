package ru.job4j.tracker;

import java.util.List;

public class FindItemByNameAction implements UserAction {
    private final Output out;

    public FindItemByNameAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find items by name";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        out.println("=== Write search name ====");
        String findName = input.askStr("search name: ");
        List<Item> listFindName = tracker.findByName(findName);
        if (listFindName.size() == 0) {
            out.println("=== Applications with this name was not found ====");
        } else {
            for (Item value : listFindName) {
                out.println(value);
            }
        }
        return true;
    }
}
