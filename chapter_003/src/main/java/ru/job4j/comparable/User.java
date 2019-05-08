package ru.job4j.comparable;

public class User implements Comparable<User> {
    private String name;
    private int age;

    public User(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(User o) {
        int compare = this.name.compareTo(o.name);
        return  compare == 0 ? this.age - o.age : compare;
    }
}
