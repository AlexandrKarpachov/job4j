package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Aleksander Karpachov
 * @since 01.06.2019
 */
public class CustomArrayList<T> implements Iterable<T> {

    private int modCount = 0;
    /**
     * default array size
     */
    private int size = 4;
    /**
     * underlying array
     */
    private Object[] storage = new Object[size];
    /**
     * counter of values
     */
    private int count = 0;

    /**
     * The method adds values to the collection
     */
    public void add(T value) {
        if (this.count >= size) {
            this.grow();
        }
        storage[count++] = value;
        modCount++;
    }

    /**
     * The method returns values by input index
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= count) {
            throw new IndexOutOfBoundsException();
        }
        return (T) storage[index];
    }

    /**
     * The method doubles a size of underlying array
     */
    private void grow() {
        if (size == Integer.MAX_VALUE) {
            throw new OutOfMemoryError();
        } else if (Integer.MAX_VALUE / size > 2) {
            size *= 2;
        } else {
            size = Integer.MAX_VALUE;
        }
        this.storage = Arrays.copyOf(this.storage, size);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int cursor = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return cursor < count;
            }


            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) storage[cursor++];
            }
        };
    }
}
