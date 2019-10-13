package ru.job4j.servlets;

import java.util.List;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 12.10.2019
 */
public interface Store {

    int generateID();

    boolean add(User user);

    boolean update(User user);

    boolean delete(User user);

    List<User> findAll();

    User findById(User user);
}
