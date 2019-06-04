package ru.job4j.set;

import ru.job4j.generics.SimpleArray;

import java.util.Iterator;

/**
 * @author Aleksander Karpachov
 * @since 05.06.2019
 */
public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> list;

    public SimpleSet(int size) {
        this.list = new SimpleArray<>(size);
    }

    /**
     * Adds values to set
     */
    public void add(T value) {
        if (!this.contains(value)) {
            list.add(value);
        }
    }

    /**
     * Checks that value already exist in this set
     */
    private boolean contains(T value) {
        boolean result = false;
        if (value == null) {
            for (T item : list) {
                if (item == null) {
                    result = true;
                    break;
                }
            }
        } else {
            for (T item : list) {
                if (value.equals(item)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }


    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
