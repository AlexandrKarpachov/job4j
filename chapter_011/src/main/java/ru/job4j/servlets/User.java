package ru.job4j.servlets;

import java.util.Objects;

public class User {
    private final Integer id;
    private final String login;
    private String name;
    private String email;
    private String createDate;



    public User(Integer id, String login, String name, String email) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.email = email;
    }

    public User(Integer id, String login, String name, String email, String createDate) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.email = email;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id.equals(user.id)
                && login.equals(user.login)
                && name.equals(user.name)
                && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, name, email);
    }

    @Override
    public String toString() {
        return String.format("User{id=%s, name=%s, login=%s, email=%s", id, name, login, email);
    }
}
