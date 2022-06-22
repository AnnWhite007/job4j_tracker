package ru.job4j.tracker;

public class FindItemByIdAction implements UserAction {
    private final Output out;

    public FindItemByIdAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find item by Id";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        out.println("=== Write id ====");
        int id = input.askInt("id: ");
        Item findId = tracker.findById(id);
        if (findId == null) {
            out.println("=== Application with this id was not found ====");
        } else {
            out.println(findId);
        }
        return true;
    }
}
