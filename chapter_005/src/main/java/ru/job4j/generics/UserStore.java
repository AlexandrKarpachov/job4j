package ru.job4j.generics;


/**
 * @author Aleksander Karpachov
 * @version $Id$
 * @since 31.05.2019
 */
public class UserStore extends AbstractStore<User> {
    public UserStore(int size) {
        super(size);
    }
}
