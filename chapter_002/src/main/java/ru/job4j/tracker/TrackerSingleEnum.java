package ru.job4j.tracker;

import java.util.List;

public enum TrackerSingleEnum {
    INSTANCE;
    Tracker track = new Tracker();

        /**
         * The method that implements adding an application to the repository
         * @param item new application
         */
        public Item add(Item item) {
            return this.track.add(item);
        }

        /**
         * Method replaces application with {@param id} on the {@param item}
         * @param id application that replace
         * @param item application for which is replaced
         * @return true if operation was done or false if application with such id
         * does not exist
         */
        public boolean replace(String id, Item item) {
            return track.replace(id, item);
        }

        /**
         * Method deleted application with {@param id}
         * @param id key id
         * @return true
         */
        public boolean delete(String id) {
            return this.track.delete(id);
        }

        /**
         * Method returns all applications
         * @return new array with applications
         */
        public List<Item> findAll() {
            return this.track.findAll();
        }

        /**
         * Method return array with applications whose field name is equal to
         * {@param key}
         * @param key name
         * @return new array with names {@param key}
         */
        public List<Item> findByName(String key) {
            return this.track.findByName(key);
        }

        /**
         * Method searching application by id
         * @param id application
         * @return found application or null if application with {@param id} does
         * not exist
         */
        public Item findById(String id) {
            return this.track.findById(id);
        }
}
