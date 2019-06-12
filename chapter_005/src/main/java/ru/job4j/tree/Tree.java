package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;
    private int modCount = 0;

    public Tree(E root) {
        this.root = new Node<>(root);
    }

    public boolean isBinary() {
        Iterator<Node<E>> iterator = this.nodeIterator();
        var result = true;
        while (iterator.hasNext()) {
            if (iterator.next().leaves().size() > 2) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean add(E parent, E child) {
        var pNode = findBy(parent);
        if (pNode.isEmpty()) {
            throw new NoSuchElementException("Such parent does not exists");
        }
        var result = false;
        var isPresent = pNode.get().leaves()
                .stream()
                .anyMatch(x -> x.eqValue(child));
        if (!isPresent) {
            pNode.get().add(new Node<>(child));
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }


    @Override
    public Iterator<E> iterator() {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(root);
        return new Iterator<>() {
            private Node<E> current = root;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return !data.isEmpty();
            }

            @Override
            public E next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                this.current = data.poll();
                data.addAll(current.leaves());
                return current.getValue();
            }
        };
    }

    public Iterator<Node<E>> nodeIterator() {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(root);
        return new Iterator<>() {
            private Node<E> current = root;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return !data.isEmpty();
            }

            @Override
            public Node<E> next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                this.current = data.poll();
                data.addAll(current.leaves());
                return this.current;
            }
        };
    }
}
