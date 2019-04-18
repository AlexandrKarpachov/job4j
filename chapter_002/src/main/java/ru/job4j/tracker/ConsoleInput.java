package ru.job4j.tracker;

import java.util.Scanner;

public class ConsoleInput implements Input {
    public String ask(String message) {
        Scanner input = new Scanner(System.in);
        System.out.println(message);
        return input.nextLine();
    }

}
