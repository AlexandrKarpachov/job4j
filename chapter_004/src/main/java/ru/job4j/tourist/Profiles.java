package ru.job4j.tourist;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aleksand Karpachov
 * @since 19.05.2019
 * Class for process client data
 */
public class Profiles {
    /**
     * Method retrieves sorted list of unique addresses from client list
     * @param profiles list
     * @return address list
     */
    List<Address> collect(List<Profile> profiles) {
        return profiles.stream()
                .map(Profile::getAddress)
                .sorted(Comparator.comparing(Address::getCity))
                .distinct()
                .collect(Collectors.toList());
    }
}
