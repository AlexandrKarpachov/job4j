package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * {@code CustomLinkedList} provides a generic linked list functionality
 *
 * @author Alexander Karpachov
 * @since 31.05.2019
 */
public class CustomLinkedList<T> implements Iterable<T> {
    private Node<T> first;
    private int count = 0;
    private int modCount = 0;

    /**
     * Adds value to the end of list
     */
    public void add(T value) {
        if (count == 0) {
            first = new Node<>(value);
        } else {
            Node<T> last = getNode(count - 1);
            last.next = new Node<>(value);
        }
        count++;
        modCount++;
    }

    /**
     * Returns value by index
     */
    public T get(int index) {
        if (index >= this.count) {
            throw new IndexOutOfBoundsException();
        }
        return this.getNode(index).value;
    }

    /**
     * Returns node by index
     */
    private Node<T> getNode(int index) {
        Node<T> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }

    /**
     * Returns iterator with fail - fast behavior
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> current = first;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                T result = current.value;
                current = current.next;
                return result;
            }
        };
    }
}

/**
 * Node for storing value
 */
class Node<T> {
    Node<T> next;
    T value;

    Node(T value) {
        this.value = value;
    }
}
