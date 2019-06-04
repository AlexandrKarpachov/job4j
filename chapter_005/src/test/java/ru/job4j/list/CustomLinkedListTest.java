package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CustomLinkedListTest {

    CustomLinkedList<Integer> list;
    @Before
    public void fillingCollection() {
        list = new CustomLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
    }

    @Test
    public void whenGetByIndex() {
        assertThat(list.get(2), is(3));
    }


    @Test
    public void iterator() {
        Iterator<Integer> iterator = list.iterator();
        List<Integer> expected = List.of(1, 2, 3, 4);
        List<Integer> actual = new ArrayList<>();

        while (iterator.hasNext()) {
            actual.add(iterator.next());
        }

        assertThat(expected, is(actual));
    }
}