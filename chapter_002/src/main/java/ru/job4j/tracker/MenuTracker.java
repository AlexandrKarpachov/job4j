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
        this.actions.add(new AddItem(0, "0. Add new Item\r\n"));
        this.actions.add(new ShowAllItems(1, "1. Show all items\r\n"));
        this.actions.add(new EditItem(2, "2. Edit item\r\n"));
        this.actions.add(new DeleteItem(3, "3. Delete item\r\n"));
        this.actions.add(new FindById(4, "4. Find item by Id\r\n"));
        this.actions.add(new FindByName(5, "5. Find items by name\r\n"));
    }

    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.print(action.info());
            }
        }
    }

    /**
     * This class realize adding a new application in repository.
     */
    private class AddItem extends BaseAction {
        public AddItem(int key, String name) {
            super(key, name);
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
    }

    /**
     * This class prints all applications name + id
     */
    private class ShowAllItems extends BaseAction {
        public ShowAllItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            List<Item> application = tracker.findAll();
            for (Item item : application) {
                System.out.format("%s %s\n%d\n", item.getName(), item.getId(), item.getTime());
            }
        }
    }

    /**
     * This class edit name and description of application with entered id
     */
    private class EditItem extends BaseAction {
        public EditItem(int key, String name) {
            super(key, name);
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
    }

    /**
     * This class delete the Application by ID
     */
    private class DeleteItem extends BaseAction {
        public DeleteItem(int key, String name) {
            super(key, name);
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
    }

    /**
     * This class displays application with entered id
     *  if it exists
     */
    private class FindById extends BaseAction {
        public FindById(int key, String name) {
            super(key, name);
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
    }

    /**
     * This class displays all applications with entered name
     * if they exists
     */
    private class FindByName extends BaseAction {
        public FindByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please, enter the Name");
            List<Item> found = tracker.findByName(name);
            if (found.size() < 1) {
                System.out.println("Sorry, Application with such Name does not exist");
            } else {
                for (Item item: found) {
                    System.out.format("%s %s\r\n%d\r\n", item.getName(), item.getDecs(), item.getTime());
                }
            }
        }
    }
}