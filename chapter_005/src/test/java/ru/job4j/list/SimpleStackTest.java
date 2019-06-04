package ru.job4j.list;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleStackTest {

    @Test
    public void whenPushThenPoll() {
        var stack = new SimpleStack<Integer>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenPollMoreThenPushThenException() {
        var stack = new SimpleStack<Integer>();

        stack.poll();
    }

    @Test
    public void whenPushThreeTimesThenTwicePollThenSize1() {
        var stack = new SimpleStack<Integer>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.poll();
        stack.poll();
        assertThat(stack.size(), is(1));

    }

}