package ru.job4j.tourist;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aleksand Karpachov
 * @since 19.05.2019
 * Class for process client data
 */
public class Profiles {
    /**
     * Method retrieves addresses from list of clients
     * @param profiles list
     * @return address list
     */
    List<Address> collect(List<Profile> profiles) {
        return profiles.stream()
                .map(Profile::getAddress)
                .collect(Collectors.toList());
    }
}
