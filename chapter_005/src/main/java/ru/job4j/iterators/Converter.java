package ru.job4j.iterators;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Alexander Karpachov
 * @since 30.05.2019
 */
public class Converter {

    /**
     * Method returns one iterator which iterate all iterators in received iterator
     * @param it Iterator of iterators
     * @return Iterator that iterate all iterators in input iterator.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            private Iterator<Integer> currentIt = Collections.emptyIterator();

            @Override
            public boolean hasNext() {
                if (!currentIt.hasNext() && it.hasNext()) {
                    currentIt = it.next();
                    while (it.hasNext() && !currentIt.hasNext()) {
                        currentIt = it.next();
                    }
                }
                return currentIt.hasNext();
            }

            @Override
            public Integer next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return currentIt.next();
            }
        };
    }
}