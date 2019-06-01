package ru.job4j.generics;

/**
 * @author Aleksander Karpachov
 * @version $Id$
 * @since 31.05.2019
 */
public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}