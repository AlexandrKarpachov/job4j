package ru.job4j.comparable;

import java.util.*;

/**
 * @author Aleksandr Karpachov
 * @since 08.05.2019
 */
public class UserSort {
    /**
     * returns Set<User> with comparator which sort
     * by age
     * @param list input list with Users
     */
    public Set<User> sort(List<User> list) {
        Comparator<User> comparator = new Comparator<>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        };
        Set<User> result = new TreeSet<>(comparator);
        result.addAll(list);
        return result;
    }
}
