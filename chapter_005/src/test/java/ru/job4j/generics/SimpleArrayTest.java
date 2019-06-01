package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class SimpleArrayTest {
    SimpleArray<Integer> array;

    @Before
    public void addingElements() {
        this.array = new SimpleArray<>(5);
        this.array.add(1);
        this.array.add(2);
        this.array.add(3);
        this.array.add(4);
        this.array.add(5);
    }

    @Test
    public void whenGet() {
        assertThat(this.array.get(2), is(3));
    }

    @Test
    public void whenRemoveThenItemsShifted() {
        this.array.remove(2);
        assertThat(this.array.get(2), is(4));
    }

    @Test
    public void whenSetThenItemChanged() {
        this.array.set(2, 7);

        assertThat(this.array.get(2), is(7));
    }

    @Test(expected = ArrayOverflowException.class)
    public void addMoreThenSizeElementsThenException() {
        this.array.add(6);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setOutOfBoundIndexThenException() {
        this.array.set(6, 5);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenWasAttemptToDeleteNotExistingItemThanException() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.remove(2);
    }

    @Test
    public void iteratorTesting() {
        Iterator<Integer> iterator = this.array.iterator();
        List<Integer> actual = new ArrayList<>();
        List<Integer> expected = List.of(1, 2, 3, 4, 5);

        while (iterator.hasNext()) {
            actual.add(iterator.next());
        }

        assertThat(actual, is(expected));
    }

}