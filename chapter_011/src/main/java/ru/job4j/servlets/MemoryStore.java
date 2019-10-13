package ru.job4j.servlets;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryStore implements Store {
    private final static MemoryStore INSTANCE = new MemoryStore();
    private final static AtomicInteger ID_COUNTER = new AtomicInteger(0);
    private final static ConcurrentHashMap<Integer, User> STORAGE = new ConcurrentHashMap<>();

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public int generateID() {
        return ID_COUNTER.getAndIncrement();
    }

    @Override
    public boolean add(User user) {
        return STORAGE.putIfAbsent(user.getId(), user) == null;
    }

    @Override
    public synchronized boolean update(User user) {
        var result = false;
        var oldUser = STORAGE.get(user.getId());
        if (oldUser != null) {
            oldUser.setCreateDate(user.getCreateDate());
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        return STORAGE.remove(user.getId()) != null;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(STORAGE.values());
    }

    @Override
    public User findById(User user) {
        return STORAGE.get(user.getId());
    }
}
