package ru.job4j.set;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;

public class UserConvertTest {
    @Test
    public void whenPut3UsersAndGot3Users() {
        UserConvert convert = new UserConvert();
        List<User> users = List.of(
                new User(1, "Vasya", "Moscow"),
                new User(2, "Fedor", "Kiev"),
                new User(3, "Ivan", "Minsk")
        );
        HashMap<Integer, User> map = convert.process(users);
        assertThat(map.size(), is(3));
    }

    @Test
    public void whenPut3UsersAndGetById() {
        UserConvert convert = new UserConvert();
        List<User> users = List.of(
                new User(1, "Vasya", "Moscow"),
                new User(2, "Fedor", "Kiev"),
                new User(3, "Ivan", "Minsk")
        );
        HashMap<Integer, User> map = convert.process(users);
        assertThat(map.get(2).getName(), is("Fedor"));
    }
}
