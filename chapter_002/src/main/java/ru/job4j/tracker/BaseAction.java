package ru.job4j.tracker;

public abstract class BaseAction implements UserAction {
    private final String name;
    private final int key;

    public BaseAction(int key, String name) {
        this.name = name;
        this.key = key;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public String info() {
        return this.name;
    }
}
