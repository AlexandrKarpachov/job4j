package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
public class TrackerSingleTest {

    @Test
    public void whenCreateTwoDifferentTrackersThenOnlyOneTrackerSingleEnum() {
        TrackerSingleEnum first = TrackerSingleEnum.INSTANCE;
        Item test1 = first.add(new Item("test1", "test desc2"));
        Item test2 = first.add(new Item("test2", "test desc2"));
        Item[] expected = {test1, test2};
        TrackerSingleEnum second = TrackerSingleEnum.INSTANCE;
        Item[] actual = second.findAll();
        assertThat(actual, is(expected));
    }

    @Test
    public void trackerSingleLazy1() {
        TrackerSingleLazy1 first = TrackerSingleLazy1.getInstance();
        Item test1 = first.add(new Item("test1", "test desc2"));
        Item test2 = first.add(new Item("test2", "test desc2"));
        Item[] expected = {test1, test2};
        TrackerSingleLazy1 second = TrackerSingleLazy1.getInstance();
        Item[] actual = second.findAll();
        assertThat(actual, is(expected));
    }

    @Test
    public void trackerSingleEager() {
        TrackerSingleEager first = TrackerSingleEager.getInstance();
        Item test1 = first.add(new Item("test1", "test desc2"));
        Item test2 = first.add(new Item("test2", "test desc2"));
        Item[] expected = {test1, test2};
        TrackerSingleEager second = TrackerSingleEager.getInstance();
        Item[] actual = second.findAll();
        assertThat(actual, is(expected));
    }

    @Test
    public void trackerSingleLazy2() {
        TrackerSingleLazy2 first = TrackerSingleLazy2.getInstance();
        Item test1 = first.add(new Item("test1", "test desc2"));
        Item test2 = first.add(new Item("test2", "test desc2"));
        Item[] expected = {test1, test2};
        TrackerSingleLazy2 second = TrackerSingleLazy2.getInstance();
        Item[] actual = second.findAll();
        assertThat(actual, is(expected));
    }
}
