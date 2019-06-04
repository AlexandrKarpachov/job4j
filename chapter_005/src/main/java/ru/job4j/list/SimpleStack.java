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

    /**
     * returns last input item and deletes it from stack
     */
    public T poll() {
        if (this.storage.getSize() == 0) {
            throw new NoSuchElementException();
        }
        return storage.delete();
    }

    /**
     * Adds value to stack
     */
    public void push(T value) {
        storage.add(value);
    }

    /**
     * Returns amount of elements in stack
     */
    public int size() {
        return this.storage.getSize();
    }

    /**
     * Returns <tt>true</tt> if stack is empty. Otherwise <tt>false</tt>.
     */
    public boolean isEmpty() {
        return this.storage.getSize() == 0;
    }
}
