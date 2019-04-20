package ru.job4j.tracker;

import java.util.Date;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {

    /**
     * Getting data from user
     */
    private final Input input;
    /**
     * Applications repository
     */
    private final Tracker tracker;

    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }

    /**
     * The constructor initializes the fields.
     *
     * @param input   data input.
     * @param tracker application repository
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * The main cycle of the program
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        do {
            menu.show();
            menu.select(Integer.parseInt(input.ask("select: ")));
        } while (!"y".equals(this.input.ask("Exit ? (y): ")));
    }
}

