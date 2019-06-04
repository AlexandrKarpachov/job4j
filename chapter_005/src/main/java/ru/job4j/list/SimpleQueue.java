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
        return this.out.poll();
    }

    /**
     * Adds value to queue
     */
    public void push(T value) {
        in.push(value);

    }

    /**
     * Returns amount of elements in queue
     */
    public int size() {
        return in.size() + out.size();
    }

    /**
     * Returns <tt>true</tt> if queue is empty. Otherwise <tt>false</tt>.
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }
}