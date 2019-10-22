package ru.job4j.servlets.logic;


import ru.job4j.servlets.logic.Validate;
import ru.job4j.servlets.models.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 22.10.2019
 */
public class ValidateStub implements Validate {
    private final Map<Integer, User> storage = new HashMap<>();
    private int ids = 0;

    public ValidateStub() {
        storage.put(ids, new User.Builder().withID(ids)
                .withName("testName")
                .withEmail("test@Mail.com")
                .withLogin("testLogin")
                .build());
        ids++;
    }

    @Override
    public int generateID() {
        return ids++;
    }

    @Override
    public boolean add(User user) {
        return storage.putIfAbsent(user.getId(), user) == null;
    }

    @Override
    public boolean update(User user) {
        var result = false;
        var oldUser = storage.get(user.getId());
        if (oldUser != null) {
            storage.replace(oldUser.getId(), user);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        return storage.remove(user.getId()) != null;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public User findById(User user) {
        return storage.get(user.getId());
    }

    @Override
    public User findByLogin(User user) {
        User result = null;
        for (User u : storage.values()) {
            if (u.getLogin().equals(user.getLogin())) {
                result = u;
                break;
            }
        }
        return result;
    }

}
