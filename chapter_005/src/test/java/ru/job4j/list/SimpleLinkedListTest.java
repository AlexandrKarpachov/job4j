package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedListTest {
    private SimpleLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        this.list = new SimpleLinkedList<>();
        this.list.add(1);
        this.list.add(2);
        this.list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(this.list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(this.list.getSize(), is(3));
    }

    @Test
    public void whenDeleteElementThenSecondBecameFirst() {
        this.list.delete();

        assertThat(this.list.get(0), is(2));
    }

    @Test
    public void whenDeleteElementThenTakeFirstItem() {
        int item = this.list.delete();

        assertThat(item, is(3));
    }

}
