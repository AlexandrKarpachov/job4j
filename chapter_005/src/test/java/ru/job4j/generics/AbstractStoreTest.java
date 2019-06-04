package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class AbstractStoreTest {
    @Test
    public void whenAddThenGet() {
        UserStore store = new UserStore(5);
        store.add(new User("n1"));

        var user = store.findById("n1");

        assertThat(user, is(new User("n1")));
    }

    @Test
    public void whenTryToGetNotExistingItemThenNull() {
        UserStore store = new UserStore(5);
        store.add(new User("n1"));

        assertThat(store.findById("n2"), is(nullValue()));
    }

    @Test
    public void whenAddThenDelete() {
        RoleStore store = new RoleStore(5);
        store.add(new Role("n2"));

        assertThat(store.delete("n2"), is(true));
        assertThat(store.findById("n2"), is(nullValue()));
    }

    @Test
    public void whenReplaceItemThenOk() {
        RoleStore store = new RoleStore(5);
        store.add(new Role("n2"));
        store.replace("n2", new Role("n3"));

        assertThat(store.findById("n2"), is(nullValue()));
        assertThat(store.findById("n3"), is(new Role("n3")));
    }



}
