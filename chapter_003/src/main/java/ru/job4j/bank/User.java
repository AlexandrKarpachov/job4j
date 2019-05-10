package ru.job4j.bank;

import java.util.Objects;

/**
 * @author Alexandr Karpachov
 * @since 09.05.2019
 */
public class User {
    private String name;
    private String passport;


    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(getName(), user.getName())
                && Objects.equals(getPassport(), user.getPassport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPassport());
    }
}
