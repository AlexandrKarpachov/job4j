package ru.job4j.tracker;

import java.util.*;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker implements ITracker {
    /**
     * Application stored array
     */
    private final ArrayList<Item> items = new ArrayList<>();

    /**
     * Cell pointer for new application
     */
    private int id = 0;

    /**
     * The method that implements adding an application to the repository
     * @param item new application
     */
    public Item add(Item item) {
        item.setId(String.valueOf(this.id++));
        this.items.add(item);
        return item;
    }

    /**
     * Method replaces application with {@param id} on the {@param item}
     * @param id application that replace
     * @param item application for which is replaced
     * @return true if operation was done or false if application with such id
     * does not exist
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public boolean replace(String id, Item item) {
        boolean result = false;
        Item old = this.findById(id);
        if (old != null) {
            item.setId(old.getId());
            Collections.replaceAll(this.items, old, item);
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
        return this.items.remove(this.findById(id));
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
        for (int i = 0; i < this.id; i++) {
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
        for (Item item: this.items) {
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

}