package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * {@code SimpleHashMap} provides basic map functionality
 *
 * @author Aleksander Karpachov
 * @since 01.06.2019
 */
public class SimpleHashMap<K, V> {
    static final float LOAD_FACTOR = 0.75f;
    private int size = 16;
    private int count = 0;
    private int modCount = 0;


    private Entry<K, V>[] table;

    @SuppressWarnings("unchecked")
    public SimpleHashMap() {
        table = new Entry[this.size];
    }

    @SuppressWarnings("unchecked")
    public SimpleHashMap(int size) {
        this.table = new Entry[size];
        this.size = size;
    }

    /**
     * Adds pair key - value to map. If in map already exists the same key then overwrites value.
     * If was collision with different keys doesn't overwrite value.
     *
     * @return <tt>true</tt> if operation successful. If some value with different key
     * stores at sell with the same index which was obtain by function getIndex(key), returns <tt>false</tt>
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        if (count == (int) LOAD_FACTOR * size || size == 0) {
            this.grow();
        }
        int index = this.getIndex(key);
        if (table[index] == null) {
            table[index] = new Entry<>(key, value);
            count++;
            modCount++;
            result = true;
        } else if (table[index].key.equals(key)) {
            table[index].value = value;
            result = true;
        }

        return result;
    }

    /**
     * Doubles size if it possible and runs {@code relocate()}
     */
    private void grow() {
        if (this.size != Integer.MAX_VALUE) {
            if (size == 0) {
                size = 4;
            } else if (Integer.MAX_VALUE / size > 2) {
                size *= 2;
            } else {
                size = Integer.MAX_VALUE;
            }
            this.relocate();
        }
    }

    /**
     * Creates new storage which has double size and relocate to it
     * all entries.
     */
    @SuppressWarnings("unchecked")
    private void relocate() {
        Entry<K, V>[] oldTable = this.table;
        table = new Entry[size];
        for (Entry<K, V> entry : oldTable) {
            if (entry != null) {
                table[getIndex(entry.key)] = entry;
            }
        }
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns value by key
     */
    public V get(K key) {
        int index = this.getIndex(key);
        return table[index].value;
    }

    /**
     * deletes entry by key..
     */
    public boolean delete(K key) {
        boolean result = false;
        int index = this.getIndex(key);
        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
    }


    /**
     * Generates random number less then <tt>size</tt> from input key
     */
    private int getIndex(K key) {
        int result = 0;
        if (key != null) {
            int h = key.hashCode();
            result = ((h) ^ (h >>> 16)) & (this.size - 1);
        }
        return result;
    }


    public Iterator<K> keyIterator() {
        return new Iterator<>() {
            int expModCount = modCount;
            int count = 0;
            @Override
            public boolean hasNext() {
                while (this.count < size && table[count] == null) {
                    count++;
                }
                return count < size;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                int index = count++;
                return table[index].key;
            }
        };
    }

    public Iterator<V> valueIterator() {
        return new Iterator<>() {
            int expModCount = modCount;
            int count = 0;
            @Override
            public boolean hasNext() {
                while (this.count < size && table[count] == null) {
                    count++;
                }
                return count < size;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                int index = count++;
                return table[index].value;
            }
        };
    }

    /**
     * Class for storing pair key - value.
     */
    static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}


