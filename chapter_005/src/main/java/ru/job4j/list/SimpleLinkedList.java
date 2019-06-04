package ru.job4j.list;


/**
 * {@code SimpleArrayList} provides Linked list functionality.
 *
 * @author Alexander Karpachov
 * @since 30.05.2019
 */
public class SimpleLinkedList<E> {

    private int size;
    private Node<E> first;

    /**
     * The method inserts data at the top of the list
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * The method returns first item and deletes it from list
     */
    public E delete() {
        Node<E> f = this.first;
        E result = this.first.data;
        this.first = this.first.next;
        f.next = null;
        this.size--;
        return result;
    }

    /**
     * The method returns element by index.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * The method returns collection size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Class for storing data.
     */
    private static class Node<E> {

        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
}