package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Application stored array
     */
    private final Item[] items = new Item[100];

    /**
     * Cell pointer for new application
     */
    private int position = 0;

    /**
     * The method that implements adding an application to the repository
     * @param item new application
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Method replaces application with {@param id} on the {@param item}
     * @param id application that replace
     * @param item application for which is replaced
     * @return true if operation was done or false if application with such id
     * does not exist
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        int index = indexOf(id);
        if (index != -1) {
            this.items[index] = item;
            result = true;
        }
        return result;
    }

    /**
     * Method deleted application with {@param id}
     * @param id key id
     * @return true
     */
    public boolean delete(String id) {
        boolean result = false;
        int index = indexOf(id);
        if (index != -1) {
            System.arraycopy(this.items, index + 1, this.items, index, this.position - index);
            result = true;
            this.position--;
        }
        return result;
    }

    /**
     * This method search first index of Item in items with
     * put id
     * @param id of searching Item
     * @return int index of first found Item
     */
    private int indexOf(String id) {
        int result = -1;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * Method returns all applications
     * @return new array with applications
     */
    public Item[] findAll() {
        return Arrays.copyOf(items, position);
    }

    /**
     * Method return array with applications whose field name is equal to
     * {@param key}
     * @param key name
     * @return new array with names {@param key}
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[position];
        int count = 0;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getName().equals(key)) {
                result[count++] = this.items[i];
            }
        }
        return Arrays.copyOf(result, count);
    }
    /**
     * Method searching application by id
     * @param id application
     * @return found application or null if application with {@param id} does
     * not exist
     */
    public Item findById(String id) {
        Item result = null;
        int index = indexOf(id);
        if (index != -1) {
            result = items[index];
        }
        return result;
    }

    /**
     * Method generate the unique key for application
     * Thug
     * Since an application doesn't have unique fields. For identification we need a unique key.
     * @return Unique key.
     */
    private String generateId() {
        return String.valueOf(new Date().getTime() + new Random().nextLong());
    }
}