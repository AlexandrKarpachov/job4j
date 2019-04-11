package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        BubbleSort test = new BubbleSort();
        int[] expect = new int[] {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
        int[] actual = test.sort(new int[] {1, 5, 4, 2, 3, 1, 7, 8, 0, 5});
        assertThat(expect, is(actual));
    }

    @Test
    public void whenSortArrayWithThreeElementsThenSortedArray() {
        BubbleSort test = new BubbleSort();
        int[] expect = new int[] {1, 2, 3};
        int[] actual = test.sort(new int[] {3, 1, 2});
        assertThat(expect, is(actual));
    }

    @Test
    public void whenSortArrayWithOneElementsThenSortedArray() {
        BubbleSort test = new BubbleSort();
        int[] expect = new int[] {1};
        int[] actual = test.sort(new int[] {1});
        assertThat(expect, is(actual));
    }

    @Test
    public void whenSortArrayWithoutElementsThenSortedArray() {
        BubbleSort test = new BubbleSort();
        int[] expect = new int[] {};
        int[] actual = test.sort(new int[] {});
        assertThat(expect, is(actual));
    }

}