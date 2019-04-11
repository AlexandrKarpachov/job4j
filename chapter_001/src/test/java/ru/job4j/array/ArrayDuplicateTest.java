package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate test = new ArrayDuplicate();
        String[] expected = new String[] {"Привет", "Мир", "Супер"};
        String[] actual = test.remove(new String[]{"Привет", "Мир", "Привет", "Супер", "Мир"});
        assertThat(actual, is(expected));
    }

    @Test
    public void whenArrayWithoutDuplicate() {
        ArrayDuplicate test = new ArrayDuplicate();
        String[] expected = new String[] {"Привет", "Мир", "Супер"};
        String[] actual = test.remove(new String[]{"Привет", "Мир", "Супер"});
        assertThat(actual, is(expected));
    }

    @Test
    public void whenArrayWithOnlyDuplicateThenArrayWithoutDuplicate() {
        ArrayDuplicate test = new ArrayDuplicate();
        String[] expected = new String[] {"Мир"};
        String[] actual = test.remove(new String[]{"Мир", "Мир", "Мир"});
        assertThat(actual, is(expected));
    }
    @Test
    public void whenArrayWith4and2DuplicateThenArrayWithoutDuplicate() {
        ArrayDuplicate test = new ArrayDuplicate();
        String[] expected = new String[] {"a", "b"};
        String[] actual = test.remove(new String[]{"a", "a", "a", "a", "b", "b"});
        assertThat(actual, is(expected));
    }

    @Test
    public void whenArrayWithManyDuplicateThenArrayWithoutDuplicate() {
        ArrayDuplicate test = new ArrayDuplicate();
        String[] expected = new String[] {"a", "c", "b"};
        String[] actual = test.remove(new String[]{"a", "a", "a", "a", "b", "b", "c"});
        assertThat(actual, is(expected));
    }

    @Test
    public void whenArrayWithOneWordThenArrayWithOneWord() {
        ArrayDuplicate test = new ArrayDuplicate();
        String[] expected = new String[] {"a"};
        String[] actual = test.remove(new String[]{"a"});
        assertThat(actual, is(expected));
    }



}