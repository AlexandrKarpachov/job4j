package ru.job4j.tourist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class ProfilesTest {
    @Test
    public void whenPutTwoProfilesThenGetTwoAddress() {
        List<Profile> clients = Arrays.asList(
                new Profile(new Address(
                        "city", "street", 2, 1)),
                new Profile(new Address(
                        "city2", "street1", 2))
        );
        List<Address> expect = Arrays.asList(
                new Address("city", "street", 2, 1),
                new Address("city2", "street1", 2)
        );
        Profiles profiles = new Profiles();
        List<Address> actual = profiles.collect(clients);
        assertThat(actual, is(expect));
    }

    @Test
    public void whenAddTwoProfilesWithSimularAddress() {
        List<Profile> clients = Arrays.asList(
                new Profile(new Address(
                        "city", "street", 2, 1)),
                new Profile(new Address(
                        "city", "street", 2,  1))
        );
        List<Address> expect = Arrays.asList(
                new Address("city", "street", 2, 1)
        );
        Profiles profiles = new Profiles();
        List<Address> actual = profiles.collect(clients);
        assertThat(actual, is(expect));
    }

    @Test
    public void whenAddThreeProfilesThenGetSortedAddressList() {
        List<Profile> clients = Arrays.asList(
                new Profile(new Address(
                        "Chernigov", "street", 2, 1)),
                new Profile(new Address(
                        "Abu - Dabi", "street", 2,  1)),
                new Profile(new Address(
                        "Boston", "street", 2,  1))
        );
        List<Address> expect = Arrays.asList(
                new Address("Abu - Dabi", "street", 2, 1),
                new Address("Boston", "street", 2, 1),
                new Address("Chernigov", "street", 2, 1)
        );
        Profiles profiles = new Profiles();
        List<Address> actual = profiles.collect(clients);
        assertThat(actual, is(expect));
    }

    @Test
    public void whenAddEmptyListThenGetSortedAddressList() {
        var clients = new ArrayList<Profile>();
        var expect = new ArrayList<Address>();
        var profiles = new Profiles();
        List<Address> actual = profiles.collect(clients);
        assertThat(actual, is(expect));
    }


}
