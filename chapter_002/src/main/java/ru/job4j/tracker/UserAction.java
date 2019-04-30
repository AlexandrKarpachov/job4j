package ru.job4j.tracker;

public interface UserAction {
    /**
     * Method returns key option
     */
    int key();

    /**
     * Main method
     * @param input object by Input type
     * @param tracker object by Tracker type
     */
    void execute(Input input, Tracker tracker);

    /**
     * Method returns info about this menu item
     * @return  menu field
     */
    String info();
}
