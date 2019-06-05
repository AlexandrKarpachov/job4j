package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 *
 * {@code SimpleArray} provides a limited generic data structure
 *
 * @author Alexander Karpachov
 * @since 31.05.2019
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int count = 0;

    public SimpleArray(int size) {
        this.array = new Object[size];
    }


    /**
     * adds elements to array
     * @param model adding item
     * @throws OverflowException if was an attempt to add more elements
     * then this array size.
     */
    public void add(T model) {
      if (count >= array.length) {
            throw new OverflowException();
        }
        array[count++] = model;
    }

    /**
     * Changes item in array by another item
     * @param index of changing item
     * @param model new item
     */
    public void set(int index, T model) {
        if (index >= this.count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index] = model;
    }

    /**
     * removes item by index and shifted all item by one
     * @param index of removed item
     */
    public void remove(int index) {
        if (index >= this.count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        array[--count] = null;
    }

    /**
     * @param index of getting item
     * @return item by input index
     */
    public T get(int index) {
        if (index >= this.count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        @SuppressWarnings("unchecked")
        T result = (T) this.array[index];
        return result;
    }

    @Override
    public String toString() {
        return Stream.of(array)
                .flatMap(Stream::ofNullable)
                .map(i -> String.format("%s, ", i.toString()))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimpleArray)) {
            return false;
        }
        SimpleArray<?> that = (SimpleArray<?>) o;
        return count == that.count
                && Arrays.equals(this.array, that.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(this.count);
        result = 31 * result + Arrays.hashCode(this.array);
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int counter = 0;
            @Override
            public boolean hasNext() {
                return this.counter < count;
            }

            @Override
            public T next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                @SuppressWarnings("unchecked")
                T result = (T) array[counter++];
                return result;
            }
        };
    }
}
