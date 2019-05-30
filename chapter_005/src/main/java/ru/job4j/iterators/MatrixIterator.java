package ru.job4j.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * {@code Matrix Iterator} returns the next element of the matrix.
 *
 * @author Alexander Karpachov
 * @since 30.05.2019
 */
public class MatrixIterator implements Iterator<Integer> {
    private final int[][] array;
    private int row = 0;
    private int column = 0;

    public MatrixIterator(int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean result = column < array[row].length;
        if (!result && row + 1 < array.length) {
            result = array[row + 1].length > 0;
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result;
        if (column < array[row].length) {
            result = array[row][column++];
        } else {
            column = 0;
            result = array[++row][column++];
        }
        return result;
    }
}
