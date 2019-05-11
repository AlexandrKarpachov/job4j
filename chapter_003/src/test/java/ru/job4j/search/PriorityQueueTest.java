package ru.job4j.search;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("urgent", 6));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }

    @Test
    public void whenPut4andGet4() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("six", 6));
        queue.put(new Task("middle", 3));
        Task first = queue.take();
        Task second = queue.take();
        Task third = queue.take();
        Task four = queue.take();
        assertThat(first.getDesc(), is("urgent"));
        assertThat(second.getDesc(), is("middle"));
        assertThat(third.getDesc(), is("low"));
        assertThat(four.getDesc(), is("six"));

    }
}