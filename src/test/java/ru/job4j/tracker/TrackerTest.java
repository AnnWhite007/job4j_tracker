package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Test
    public void whenReplace() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item("bugWithDesc");
        bugWithDesc.setName("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));

    }
    @Test
    public void whenDelete() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        int id = bug.getId();
        tracker.delete(id);
        assertNull(tracker.findById(id));
    }
    @Test
    public void whenSort() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("N"));
        Item item2 = tracker.add(new Item("A"));
        Item item3 = tracker.add(new Item("B"));
        List<Item> list = Arrays.asList(item1, item2, item3);
        assertThat(tracker.sorted(list), is(Arrays.asList(new Object[] {item2, item3, item1})));
}
    @Test
    public void whenSortReverse() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("N"));
        Item item2 = tracker.add(new Item("A"));
        Item item3 = tracker.add(new Item("B"));
        List<Item> list = Arrays.asList(item1, item2, item3);
        assertThat(tracker.sortedReverse(list), is(Arrays.asList(new Object[] {item1, item3, item2})));
    }

}