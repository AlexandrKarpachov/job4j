package ru.job4j.set;

import org.junit.Test;
import ru.job4j.generics.OverflowException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest<T> {

    public List<Integer> toList(SimpleSet<Integer> set) {
        List<Integer> result = new ArrayList<>();
        for (Integer num : set) {
            result.add(num);
        }
        return result;
    }

    @Test
    public void whenAddThreeItemThenOk() {
        SimpleSet<Integer> set = new SimpleSet<>(3);
        set.add(1);
        set.add(2);
        set.add(3);
        List<Integer> expected = List.of(1, 2, 3);
        List<Integer> actual = toList(set);

        assertThat(actual, is(expected));
    }

    @Test (expected = OverflowException.class)
    public void whenAdd3ElementsInSetWithCapacity2ThenException() {
        SimpleSet<Integer> set = new SimpleSet<>(2);
        set.add(1);
        set.add(2);
        set.add(3);
    }

    @Test
    public void whenAddRepeatingItemsThenOnlyOriginal() {
        SimpleSet<Integer> set = new SimpleSet<>(4);
        set.add(3);
        set.add(null);
        set.add(3);
        set.add(5);
        set.add(null);
        List<Integer> expected = Arrays.asList(3, null, 5);
        List<Integer> actual = toList(set);

        assertThat(actual, is(expected));
    }
}