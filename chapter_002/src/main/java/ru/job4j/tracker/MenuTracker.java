package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that realize menu and its behavior
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private List<UserAction> actions = new ArrayList<>();

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public int getActionsLength() {
        return this.actions.size();
    }

    public void fillActions() {
        this.actions.add(new AddItem());
        this.actions.add(new ShowAllItems());
        this.actions.add(new EditItem());
        this.actions.add(new DeleteItem());
        this.actions.add(new FindById());
        this.actions.add(new FindByName());
    }

    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * This class realize adding a new application in repository.
     */
    private class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Adding a new application --------------");
            String name = input.ask("Enter applications name:");
            String desc = input.ask("Enter applications description :");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ new application with getId : " + item.getId() + "-----------");
        }

        @Override
        public String info() {
            return "0. Add new Item";
        }
    }

    /**
     * This class prints all applications name + id
     */
    private class ShowAllItems implements UserAction {
        /**
         * Show all Applications constant
         */
        private static final int SHOW_ALL = 1;

        @Override
        public int key() {
            return SHOW_ALL;
        }


        @Override
        public void execute(Input input, Tracker tracker) {
            Item[] application = tracker.findAll();
            for (Item item : application) {
                System.out.format("%s %s\r\n%d\r\n", item.getName(), item.getId(), item.getTime());
            }
        }

        @Override
        public String info() {
            return "1. Show all items";
        }

    }

    /**
     * This class edit name and description of application with entered id
     */
    private class EditItem implements UserAction {
        /**
         * Menu constant for editing item
         */
        private static final int EDIT = 2;

        @Override
        public int key() {
            return EDIT;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
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
        }

        @Override
        public String info() {
            return "2. Edit item";
        }
    }

    /**
     * This class delete the Application by ID
     */
    private class DeleteItem implements UserAction {
        /**
         * Menu constant for delete item
         */
        private static final int DELETE = 3;

        @Override
        public int key() {
            return DELETE;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, Enter an ID");
            boolean result = tracker.delete(id);
            if (result) {
                System.out.format("Application with ID %s successfully deleted", id);
            } else {
                System.out.format("Application with ID %s does not exist", id);
            }
        }

        @Override
        public String info() {
            return "3. Delete item";
        }
    }

    /**
     * This class displays application with entered id
     *  if it exists
     */
    private class FindById implements UserAction {
        /**
         * Menu constant for searching item by id
         */
        private static final int FIND_ID = 4;

        @Override
        public int key() {
            return FIND_ID;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter ID");
            Item found = tracker.findById(id);
            if (found == null) {
                System.out.println("Sorry, Application with such ID does not exist");
            } else {
                System.out.format("%s %s\r\n%d\r\n", found.getName(), found.getDecs(), found.getTime());
            }
        }

        @Override
        public String info() {
            return "4. Find item by Id";
        }
    }

    /**
     * This class displays all applications with entered name
     * if they exists
     */
    private class FindByName implements UserAction {
        /**
         * Menu constant for searching item by name
         */
        private static final int FIND_NAME = 5;
        @Override
        public int key() {
            return FIND_NAME;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please, enter the Name");
            Item[] found = tracker.findByName(name);
            if (found.length < 1) {
                System.out.println("Sorry, Application with such ID does not exist");
            } else {
                for (Item item: found) {
                    System.out.format("%s %s\r\n%d\r\n", item.getName(), item.getDecs(), item.getTime());
                }
            }
        }

        @Override
        public String info() {
            return "5. Find items by name";
        }
    }
}