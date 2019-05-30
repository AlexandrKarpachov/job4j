package ru.job4j.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * {@code Even Numbers Iterator} returns the next even element of the array.
 *
 * @author Alexander Karpachov
 * @since 30.05.2019
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] array;
    private int count = -1;
    private int even;

    public EvenNumbersIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        even = getNextEven();
        return even >= 0;
    }

    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        count = even;
        return array[count];
    }

    private int getNextEven() {
        int result = -1;
        for (int i = count + 1; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                result = i;
                break;
            }
        }
        return result;
    }
}
