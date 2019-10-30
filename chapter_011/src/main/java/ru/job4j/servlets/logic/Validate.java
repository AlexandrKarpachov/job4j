package ru.job4j.servlets.logic;

import ru.job4j.servlets.models.User;

import java.util.List;

public interface Validate {
    int generateID();

    boolean add(User user);

    boolean update(User user);

    boolean delete(User user);

    List<User> findAll();

    User findById(User user);

    User findByLogin(User user);
}
