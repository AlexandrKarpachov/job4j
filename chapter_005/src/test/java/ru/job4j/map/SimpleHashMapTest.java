package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    SimpleHashMap<Integer, String> map;
    @Before
    public void assignMap() {
        map = new SimpleHashMap<>();
    }

    @Test
    public void whenInsertPairThenGet() {
        map.insert(1, "first");
        assertThat(map.get(1), is("first"));
    }


    @Test
    public void whenInsertPairThenDeleteThenEmptyMap() {
        map.insert(1, "first");

        assertThat(map.delete(1), is(true));
        assertThat(map.isEmpty(), is(true));
    }

    @Test
    public void whenInsertPairsWithTheSameKeysThenOnlyLastRamain() {
        map.insert(1, "first");
        map.insert(1, "second");

        assertThat(map.get(1), is("second"));
    }

    @Test
    public void whenTryToInsertKeyWhichGeneratesRepeatedIndex() {
        map = new SimpleHashMap<>(4);

        assertThat(map.insert(1, "first"), is(true));
        assertThat(map.insert(2, "second"), is(true));

        assertThat(map.insert(9, "third"), is(false));
    }




    @Test
    public void keyIterator() {
        map.insert(1, "first");
        map.insert(2, "second");
        map.insert(3, "third");
        List<Integer> expected = List.of(1, 2, 3);
        List<Integer> actual = new ArrayList<>();
        Iterator<Integer> iterator = map.keyIterator();

        while (iterator.hasNext()) {
            actual.add(iterator.next());
        }

        assertThat(actual, is(expected));
    }


    @Test
    public void valueIterator() {
        map.insert(1, "first");
        List<String> expected = List.of("first");
        List<String> actual = new ArrayList<>();
        Iterator<String> iterator = map.valueIterator();

        while (iterator.hasNext()) {
            actual.add(iterator.next());
        }

        assertThat(actual, is(expected));
    }
}