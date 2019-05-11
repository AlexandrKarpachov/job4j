package ru.job4j.tracker;

import java.util.*;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Application stored array
     */
    private final List<Item> items = new ArrayList<>();

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
        this.items.add(this.position++, item);
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
            this.items.set(index, item);
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
            this.items.remove(index);
            //System.arraycopy(this.items, index + 1, this.items, index, this.position - index);
            result = true;
            this.position--;
        }
        return result;
    }

    /**
     * This method search first index of Item with put id
     * in items
     * @param id of searching Item
     * @return int index of first found Item
     */
    private int indexOf(String id) {
        int result = -1;
        for (int i = 0; i < this.position; i++) {
            if (this.items.get(i).getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * Method returns a copy of list with all applications
     * @return new array with applications
     */
    public List<Item> findAll() {
        return List.copyOf(items);
    }

    /**
     * Method return List with applications whose field name is equal
     * to {@param key}
     * @param key name
     * @return new array with names {@param key}
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < this.position; i++) {
            if (this.items.get(i).getName().equals(key)) {
                result.add(this.items.get(i));
            }
        }
        return result;
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
            result = items.get(index);
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