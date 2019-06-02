package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * {@code SimpleStack} provides stack functionality.
 *
 * @author Alexander Karpachov
 * @since 30.05.2019
 */
public class SimpleStack<T> {
    private SimpleLinkedList<T> storage = new SimpleLinkedList<>();
    private int count = 0;
    /**
     * returns last input item and deletes it from stack
     */
    public T poll() {
        if (count == 0) {
            throw new NoSuchElementException();
        }
        count--;
        return storage.delete();
    }

    /**
     * Adds value to stack
     */
    public void push(T value) {
        storage.add(value);
        count++;
    }

    /**
     * Returns amount of elements in stack
     */
    public int size() {
        return count;
    }

    /**
     * Returns <tt>true</tt> if stack is empty. Otherwise <tt>false</tt>.
     */
    public boolean isEmpty() {
        return count == 0;
    }
}
