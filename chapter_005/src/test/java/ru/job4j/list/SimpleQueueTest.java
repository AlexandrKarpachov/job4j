package ru.job4j.list;

import org.junit.Test;

import java.util.NoSuchElementException;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {

    @Test
    public void whenPushThenPoll() {
        var stack = new SimpleQueue<Integer>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertThat(stack.poll(), is(1));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(3));
    }

    @Test
    public void whenPushAndPollSequenceThenOk() {
        var stack = new SimpleQueue<Integer>();

        stack.push(1);
        stack.push(2);
        stack.poll();
        stack.poll();
        stack.push(3);
        stack.push(4);

        assertThat(stack.poll(), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenPollMoreThenPushThenException() {
        var stack = new SimpleQueue<Integer>();

        stack.push(1);
        stack.poll();
        stack.poll();
    }

    @Test
    public void whenPushAndPollThenIsEmptyTrue() {
        var stack = new SimpleQueue<Integer>();

        stack.push(1);
        stack.push(2);
        stack.poll();
        stack.poll();

        assertThat(stack.isEmpty(), is(true));
    }

    @Test
    public void whenPushTwiceThenSizeIsTwo() {
        var stack = new SimpleQueue<Integer>();

        stack.push(1);
        stack.push(2);

        assertThat(stack.size(), is(2));
    }
}