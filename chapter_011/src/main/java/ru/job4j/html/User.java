package ru.job4j.html;

import java.util.Objects;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 26.10.2019
 */
public class User {
    String name;
    String surname;
    String gender;
    String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User demoUser = (User) o;
        return Objects.equals(name, demoUser.name)
                && Objects.equals(surname, demoUser.surname)
                && Objects.equals(gender, demoUser.gender)
                && Objects.equals(description, demoUser.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, gender, description);
    }

    @Override
    public String toString() {
        return "DemoUser{"
                + "name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", gender='" + gender + '\''
                + ", description='" + description + '\''
                + '}';
    }
}

