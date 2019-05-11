package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

public class PhoneDictionary {
    private List<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * returns a list of all users that contain key in any fields.
     * @param key for searching.
     * @return List of found persons.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person person: persons) {
            if (search(person, key)) {
                result.add(person);
            }
        }
        return result;
    }

    /**
     * checking that input person stored key value in Its fields
     * @param person checking person
     * @param key for searching
     */
    private boolean search(Person person, String key) {
        boolean result = false;
        if (person.getAddress().contains(key)
            || person.getName().contains(key)
            || person.getPhone().contains(key)
            || person.getSurname().contains(key)) {
            result = true;
        }
        return result;
    }
}
