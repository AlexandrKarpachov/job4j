package ru.job4j.search;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class Person {
    private String name;
    private String surname;
    private String phone;
    private String address;

    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }


    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Person person = (Person) o;
            result = this.getAddress().equals(person.getAddress())
                    && this.getName().equals(person.getName())
                    && this.getPhone().equals(person.getPhone())
                    && this.getSurname().equals(person.getSurname());
        }
        return result;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(phone)
                .append(address)
                .append(surname)
                .toHashCode();
    }
}
