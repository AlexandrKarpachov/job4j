package ru.job4j.tracker;

import java.util.Date;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /**
     * Menu constant for new application add
     */
    private static final String ADD = "0";
    /**
     * Show all Applications
     */
    private static final String SHOW_ALL = "1";
    /**
     * Menu constant for editing item
     */
    private static final String EDIT = "2";
    /**
     * Menu constant for delete item
     */
    private static final String DELETE = "3";
    /**
     * Menu constant for searching item by id
     */
    private static final String FIND_ID = "4";
    /**
     * Menu constant for searching item by name
     */
    private static final String FIND_NAME = "5";
    /**
     * Constant for out of cycle
     */
    private static final String EXIT = "6";
    /**
     * Getting data from user
     */
    private final Input input;

    /**
     * Application repository
     */
    private final Tracker tracker;

    /**
     * The constructor initializes the fields.
     * @param input data input.
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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Enter menu item: ");
            switch (answer) {
                case (ADD):
                    this.createItem();
                    break;
                case (SHOW_ALL):
                    showAll();
                    break;
                case (EDIT):
                    edit();
                    break;
                case (DELETE):
                    delete();
                    break;
                case (FIND_ID):
                    findId();
                    break;
                case (FIND_NAME):
                    findName();
                    break;
                case (EXIT):
                    exit = true;
                    break;
                default:
                    System.out.println("Plese enter correct item");

             }
        }
    }

    /**
     * This method realize adding a new application in repository.
     */
    private void createItem() {
        Date date = new Date();
        System.out.println("------------ Adding a new application --------------");
        String name = this.input.ask("Enter applications name:");
        String desc = this.input.ask("Enter applications descriotion :");
        Item item = new Item(name, desc, date.getTime());
        this.tracker.add(item);
        System.out.println("------------ new application with getId : " + item.getId() + "-----------");
    }

    /**
     * Method prints all applications name + id
     */
    private void showAll() {
        Item[] appl = tracker.findAll();
        for (Item item: appl) {
            System.out.format("%s %s\r\n%d\r\n", item.getName(), item.getId(), item.getTime());
        }
        input.ask("Press Enter to Continue");
    }

    /**
     * This method edit description of application
     */
    private void edit() {
        String id = input.ask("Please enter ID");
        Item edit = tracker.findById(id);
        if (edit == null) {
            System.out.println("Sorry, Application with such ID does not exist");
        } else {
            String name = input.ask("Enter new name");
            String desc = input.ask("Enter new description");
            Item item =  new Item(name, desc, edit.getTime());
            item.setId(id);
            tracker.replace(id, item);
        }
        input.ask("Press Enter to Continue");
    }

    /**
     * This method delete the Application by ID
     */
    private void delete() {
        String id = input.ask("Please, Enter an ID");
        boolean result = tracker.delete(id);
        if (result) {
            System.out.format("Application with ID %s successfully deleted", id);
        } else {
            System.out.format("Application with ID %s does not exist", id);
        }
        input.ask("Press Enter to Continue");
    }

    /**
     * This method displays application with entered id
     * if it exists
     */
    private void findId() {
        String id = input.ask("Please, enter ID");
        Item found = tracker.findById(id);
        if (found == null) {
            System.out.println("Sorry, Application with such ID does not exist");
        } else {
            System.out.format("%s %s\r\n%d\r\n", found.getName(), found.getDecs(), found.getTime());
        }
        input.ask("Press Enter to Continue");
    }

    /**
     * This method displays all applications with entered name
     * if they exists
     */
    private void findName() {
        String name = input.ask("Please, enter the Name");
        Item[] found = tracker.findByName(name);
        if (found.length < 1) {
            System.out.println("Sorry, Application with such ID does not exist");
        } else {
            for (Item item: found) {
                System.out.format("%s %s\r\n%d\r\n", item.getName(), item.getDecs(), item.getTime());
            }
        }
        input.ask("Press Enter to Continue");
    }

    /**
     * The Method displays menu
     */
    private void showMenu() {
        System.out.println("MENU");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
        System.out.println("Select:");
    }

    /**
     * Run the program.
     * @param args args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}