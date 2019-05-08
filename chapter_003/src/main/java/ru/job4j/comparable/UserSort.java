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

    /**
     * method return List sorted by User
     * {@param name} length
     * @param list for sorting
     */
    public List<User> sortNameLength (List<User> list) {
        Comparator<User> comparator = new Comparator<>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        };
        list.sort(comparator);
        return list;
    }

    /**
     * method sort list of User by name in lexicographical order
     * and if names are the same then sorted by age in increasing order
     * @param list for sorting
     * @return sorted List<User>
     */
    public List<User> sortByAllFields (List<User> list) {
        Comparator<User> comparator = new Comparator<>() {
            @Override
            public int compare(User o1, User o2) {
                int compare = o1.getName().compareTo(o2.getName());
                return  compare == 0 ? o1.getAge() - o2.getAge() : compare;
            }
        };
        Collections.sort(list, comparator);
        return list;
    }


}
