package ru.job4j.test;

public class WrongDividersSetException extends RuntimeException {
    public WrongDividersSetException() {
        super("Sorry, I can't exchange this value with current set");
    }
}
