package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CustomArrayListTest {
    CustomArrayList<Integer> array;

    @Before
    public void addingElements() {
        this.array = new CustomArrayList<>();
        this.array.add(1);
        this.array.add(2);
        this.array.add(3);
        this.array.add(4);
    }

    @Test
    public void whenGet() {
        assertThat(this.array.get(2), is(3));
    }

    @Test
    public void whenAddOversizeThenOk() {
        this.array.add(5);
        assertThat(this.array.get(4), is(5));
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void getOutOfBoundIndexThenException() {
        this.array.get(6);
    }


    @Test (expected = ConcurrentModificationException.class)
    public void iteratorTesting() {
        Iterator<Integer> iterator = this.array.iterator();
        List<Integer> actual = new ArrayList<>();
        List<Integer> expected = List.of(1, 2, 3, 4);

        while (iterator.hasNext()) {
            actual.add(iterator.next());
            this.array.add(7);
        }

        assertThat(actual, is(expected));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenAddElementsDuringIterationThenException() {
        Iterator<Integer> iterator = this.array.iterator();
        this.array.add(5);
        iterator.next();

    }
}
