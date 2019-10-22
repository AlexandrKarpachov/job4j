package ru.job4j.servlets.logic;

import ru.job4j.servlets.models.User;

import java.util.List;
import java.util.regex.Pattern;

public class ValidateService implements Validate {
    private static final Validate  INSTANCE = new ValidateService();
    private static final Pattern CYRILLIC = Pattern.compile("^([а-яА-Я0-9]+$)");
    private static final Pattern LATIN = Pattern.compile("^([a-zA-Z0-9]+$)");
    private static final Pattern EMAIL = Pattern.compile("^[a-z0-9._\\-]+@[a-z]+\\.[a-z]+$");
    private final Store store = DbStore.getInstance();

    private ValidateService() {
    }

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public int generateID() {
        return store.generateID();
    }

    @Override
    public boolean add(User user) {
        var result = false;
        if (this.isOriginal(user) && this.check(user)) {
            result = store.add(user);
        }
        return result;
    }

    private boolean isOriginal(User user) {
        var result = true;

        for (User eUser : store.findAll()) {
            if (eUser.getId().equals(user.getId())
                    || eUser.getLogin().equalsIgnoreCase(user.getLogin())) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean update(User user) {
        var result = false;
        if (this.findById(user) != null && check(user)) {
            result = store.update(user);
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        return store.delete(user);
    }

    @Override
    public List<User> findAll() {
        return store.findAll();
    }

    @Override
    public User findById(User user) {
        return store.findById(user);
    }

    @Override
    public User findByLogin(User user) {
        return store.findByLogin(user);
    }

    private boolean check(User user) {
        var name = user.getName();
        return (CYRILLIC.matcher(name).find() || LATIN.matcher(name).find())
                && EMAIL.matcher(user.getEmail().toLowerCase()).find()
                && LATIN.matcher(user.getLogin()).find();
    }
}
