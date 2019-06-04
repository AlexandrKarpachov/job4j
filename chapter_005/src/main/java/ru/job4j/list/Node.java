package ru.job4j.list;

public class Node<T> {
    Node<T> next;
    T value;

    public Node(T value) {
        this.value = value;
    }

    /**
     * Checks if linked list has loops.
     * @param first Node
     * @return <tt>true</tt> if sequence has loop, otherwise <tt>false</tt>.
     */
    boolean hasCycle(Node<T> first) {
        if (first == null) {
            throw new NullPointerException();
        }
        Node<T> slow = first;
        Node<T> fast = first;
        boolean isLoop = false;
        while (fast != null && !isLoop) {
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                break;
            }
            slow = slow.next;
            isLoop = slow == fast;
        }
        return isLoop;
    }
}
