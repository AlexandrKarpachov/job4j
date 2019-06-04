package ru.job4j.generics;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Base)) {
            return false;
        }
        Base base = (Base) o;
        return getId().equals(base.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}