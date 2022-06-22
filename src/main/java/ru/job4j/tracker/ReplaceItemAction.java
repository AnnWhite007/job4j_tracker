package ru.job4j.tracker;

public class ReplaceItemAction implements UserAction {
    private final Output out;

    public ReplaceItemAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Edit item";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        out.println("=== Write id ====");
        int id = input.askInt("id: ");
        out.println("=== Write new name ====");
        String newName = input.askStr("name: ");
        Item newItem = new Item(newName);
        if (tracker.replace(id, newItem)) {
            out.println("=== Successfully ====");
        } else {
            out.println("=== Error ====");
        }
        return true;
    }
}
