package ru.job4j.tracker;

public class DeleteItemAction implements UserAction {
    private final Output out;

    public DeleteItemAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete item";
    }

    @Override
    public boolean execute(Input input, Store store) {
        out.println("=== Write id ====");
        int id = input.askInt("id: ");
        if (store.delete(id)) {
            out.println("=== Successfully ====");
        } else {
            out.println("=== Error ====");
        }
        return true;
    }
}
