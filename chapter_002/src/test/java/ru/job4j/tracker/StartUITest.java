package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
public class StartUITest {

    @Test
    public void whenAddOneItem() {
        String[] sequence = {"0", "name1", "test description", "6"};
        Tracker tracker = new Tracker();
        StubInput input = new StubInput(sequence);
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("name1"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "change ticket", "enter", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenAddTwoTicketAndDelete() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("name1", "desc1"));
        Item second = tracker.add(new Item("name2", "desc2"));
        Item[] expected = new Item[] {first};
        Input input = new StubInput(new String[]{"3", second.getId(), "enter", "6"});
        new StartUI(input, tracker).init();
        assertThat(expected, is(tracker.findAll()));
    }

    @Test
    public void whenAddOneTicketAndDelete() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("name1", "desc1"));
        Input input = new StubInput(new String[]{"3", first.getId(), "enter", "6"});
        new StartUI(input, tracker).init();
        assertThat(new Item[]{}, is(tracker.findAll()));
    }


}
