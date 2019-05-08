package ru.job4j.comparable;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserSortTest {
    @Test
    public void addTreeElements() {
        List<User> users = Arrays.asList(
                new User("Vasya", 25),
                new User("Petya", 37),
                new User("Ivan", 18)
        );
        UserSort sort = new UserSort();
        Set<User> result = sort.sort(users);
        int[] ages = new int[result.size()];
        int i = 0;
        for(User user : result) {
            ages[i++] = user.getAge();
        }
        assertThat(ages, is(new int[]{18, 25, 37}));
    }

    @Test
    public void sortByNamesLength() {
        List<User> users = Arrays.asList(
                new User("Yan", 25),
                new User("Vasya", 25),
                new User("Petya", 37),
                new User("Ivan", 18)
        );
        List<User> expected = Arrays.asList(
                new User("Yan", 25),
                new User("Ivan", 18),
                new User("Vasya", 25),
                new User("Petya", 37)
        );
        UserSort sort = new UserSort();
        List<User> actual = sort.sortNameLength(users);
        assertThat(expected, is(actual));
    }

    @Test
    public void sortByAllFields() {
        List<User> users = Arrays.asList(
                new User("Gennadiy", 18),
                new User("Vasya", 45),
                new User("Yan", 25),
                new User("Vasya", 37)

        );
        List<User> expected = Arrays.asList(
                new User("Gennadiy", 18),
                new User("Vasya", 37),
                new User("Vasya", 45),
                new User("Yan", 25)
        );
        UserSort sort = new UserSort();
        List<User> actual = sort.sortByAllFields(users);
        assertThat(expected, is(actual));
    }

}
