package ru.job4j.tracker;

import java.util.List;

public class TrackerSingleLazy1 {
    private static TrackerSingleLazy1 instance;
    private static final Tracker TRACKER = new Tracker();

    private TrackerSingleLazy1() {
    }

    public static TrackerSingleLazy1 getInstance() {
        if (instance == null) {
            instance = new TrackerSingleLazy1();
        }
        return instance;
    }


    /**
     * The method that implements adding an application to the repository
     * @param item new application
     */
    public Item add(Item item) {
        return TRACKER.add(item);
    }

    /**
     * Method replaces application with {@param id} on the {@param item}
     * @param id application that replace
     * @param item application for which is replaced
     * @return true if operation was done or false if application with such id
     * does not exist
     */
    public boolean replace(String id, Item item) {
        return TRACKER.replace(id, item);
    }

    /**
     * Method deleted application with {@param id}
     * @param id key id
     * @return true
     */
    public boolean delete(String id) {
        return TRACKER.delete(id);
    }

    /**
     * Method returns all applications
     * @return new array with applications
     */
    public List<Item> findAll() {
        return TRACKER.findAll();
    }

    /**
     * Method return array with applications whose field name is equal to
     * {@param key}
     * @param key name
     * @return new array with names {@param key}
     */
    public List<Item> findByName(String key) {
        return TRACKER.findByName(key);
    }

    /**
     * Method searching application by id
     * @param id application
     * @return found application or null if application with {@param id} does
     * not exist
     */
    public Item findById(String id) {
        return TRACKER.findById(id);
    }
}
