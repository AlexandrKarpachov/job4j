package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> persons = phones.find("Petr");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }

    @Test
    public void whenSeveralMatches() {
        PhoneDictionary phones = new PhoneDictionary();
        Person petr = new Person("Petr", "Arsentev", "534872", "Bryansk");
        Person vasya = new Person("Vasya", "Ivanov", "666666", "Kiev");
        Person evgeniy = new Person("Evgeniy", "Ivanov", "777777", "Moscow");
        Person ivan = new Person("Ivan", "Petrov", "555555", "Odessa");
        phones.add(petr);
        phones.add(vasya);
        phones.add(evgeniy);
        phones.add(ivan);

        List<Person> expected = List.of(
                vasya,
                evgeniy,
                new Person("Ivan", "Petrov", "555555", "Odessa")
        );
        List<Person> persons = phones.find("Ivan");
        assertThat(persons.equals(expected), is(true));
    }

}