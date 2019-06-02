package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * {@code SimpleQueue} provides queue functionality.
 *
 * @author Alexander Karpachov
 * @since 30.05.2019
 */
public class SimpleQueue<T> {
    private SimpleStack<T> in = new SimpleStack<>();
    private SimpleStack<T> out = new SimpleStack<>();
    private int count = 0;

    /**
     * returns first input item and deletes it from queue
     */
    public T poll() {
        if (this.in.isEmpty() && this.out.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (out.isEmpty()) {
            while (!this.in.isEmpty()) {
                this.out.push(this.in.poll());
            }
        }
        count--;
        return this.out.poll();
    }

    /**
     * Adds value to queue
     */
    public void push(T value) {
        in.push(value);
        count++;
    }

    /**
     * Returns amount of elements in queue
     */
    public int size() {
        return count;
    }

    /**
     * Returns <tt>true</tt> if queue is empty. Otherwise <tt>false</tt>.
     */
    public boolean isEmpty() {
        return count == 0;
    }
}