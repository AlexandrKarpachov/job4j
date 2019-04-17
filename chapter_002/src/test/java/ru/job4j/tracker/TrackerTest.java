package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        long created = System.currentTimeMillis();
        Item item = new Item("test1", "testDescription", created);
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteMiddleIdThenReturnWithoutItem() {
        Tracker actual = new Tracker();
        Tracker expected = new Tracker();
        Item first = new Item("test1", "testDescription", 123L);
        Item second = new Item("test1", "testDescription", 124L);
        Item third = new Item("test1", "testDescription", 124L);
        expected.add(first);
        expected.add(third);
        actual.add(first);
        actual.add(second);
        actual.add(third);
        actual.delete(second.getId());
        assertThat(expected.findAll(), is(actual.findAll()));
    }

    @Test
    public void whenDeleteLastIdThenReturnWithoutItem() {
        Tracker actual = new Tracker();
        Tracker expected = new Tracker();
        Item first = new Item("test1", "testDescription", 123L);
        Item second = new Item("test1", "testDescription", 124L);
        Item third = new Item("test1", "testDescription", 124L);
        expected.add(first);
        expected.add(second);
        actual.add(first);
        actual.add(second);
        actual.add(third);
        actual.delete(third.getId());
        assertThat(expected.findAll(), is(actual.findAll()));
    }

    @Test
    public void whenDeleteOnceIdThenReturnEmpty() {
        Tracker atual = new Tracker();
        Tracker expected = new Tracker();
        Item first = new Item("test1", "testDescription", 123L);
        atual.add(first);

        atual.delete(first.getId());
        assertThat(expected.findAll(), is(atual.findAll()));
    }

    @Test
    public void whenFindAllThenReturnArrayWithAllValues() {
        Tracker test = new Tracker();
        Tracker expected = new Tracker();
        Item first = new Item("test1", "testDescription", 123L);
        Item second = new Item("test1", "testDescription", 124L);
        Item third = new Item("test2", "testDescription", 124L);
        expected.add(first);
        expected.add(second);
        test.add(first);
        test.add(second);
        test.add(third);
        Item[] actual = test.findByName("test1");

        assertThat(expected.findAll(), is(actual));
    }

    @Test
    public void whenFindNameThenReturnArrayDuplicates() {
        Tracker test = new Tracker();
        Tracker expected = new Tracker();
        Item first = new Item("test1", "testDescription", 123L);
        Item second = new Item("test1", "testDescription", 124L);
        Item third = new Item("test2", "testDescription", 124L);
        expected.add(first);
        expected.add(second);
        test.add(first);
        test.add(second);
        test.add(third);
        Item[] actual = test.findByName("test1");
        assertThat(expected.findAll(), is(actual));
    }
}