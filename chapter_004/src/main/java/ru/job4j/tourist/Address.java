package ru.job4j.tourist;

import java.util.Objects;

/**
 * @author Aleksand Karpachov
 * @since 19.05.2019
 * Class for storing address
 */
public class Address {
    private String city;
    private String street;
    private int home;
    private int apartment;

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHome() {
        return home;
    }

    public int getApartment() {
        return apartment;
    }

    public Address(String city, String street, int home) {
        this.city = city;
        this.street = street;
        this.home = home;
    }

    public Address(String city, String street, int home, int apartment) {
        this.city = city;
        this.street = street;
        this.home = home;
        this.apartment = apartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }
        Address address = (Address) o;
        return home == address.home
                && apartment == address.apartment
                && city.equals(address.city)
                && street.equals(address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, home, apartment);
    }

    @Override
    public String toString() {
        return "Address{" + "city='" + city + '\''
                + ", street='" + street + '\''
                + ", home=" + home + ", apartment="
                + apartment + '}';
    }
}
