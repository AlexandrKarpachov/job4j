package ru.job4j.generics;

/**
 * @author Aleksander Karpachov
 * @version $Id$
 * @since 31.05.2019
 */
public abstract class Base {
    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}