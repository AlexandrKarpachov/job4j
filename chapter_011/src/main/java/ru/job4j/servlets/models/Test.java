package ru.job4j.servlets.models;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 20.10.2019
 */
public class Test {
    public static void main(String[] args) {
        User user = new User.Builder().withRole(Role.valueOf("ADMIN")).build();
        System.out.println(user.getRole().name());
        //if (user.getRole() == Role.ADMIN);
    }
}
