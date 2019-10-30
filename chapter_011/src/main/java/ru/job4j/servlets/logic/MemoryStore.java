package ru.job4j.servlets.logic;

import ru.job4j.servlets.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryStore implements Store {
    private final static MemoryStore INSTANCE = new MemoryStore();
    private final  AtomicInteger idCounter = new AtomicInteger(0);
    private final ConcurrentHashMap<Integer, User> storage = new ConcurrentHashMap<>();

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public int generateID() {
        return idCounter.getAndIncrement();
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
            oldUser.setName(user.getName());
            oldUser.setEmail(user.getEmail());
            oldUser.setCreateDate(user.getCreateDate());
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
