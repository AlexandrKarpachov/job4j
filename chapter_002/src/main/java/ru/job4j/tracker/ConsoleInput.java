package ru.job4j.tracker;

import java.util.List;
import java.util.Scanner;

public class ConsoleInput implements Input {
    @Override
    public String ask(String message) {
        Scanner input = new Scanner(System.in);
        System.out.println(message);
        return input.nextLine();
    }

    @Override
    public int ask(String question, List<Integer> range) {
        int result = -1;
        int key = Integer.parseInt(this.ask(question));
        for (int value : range) {
            if (key == value) {
                result = key;
            }
        }
        if (result == -1) {
            throw new MenuOutException("Invalid value, out of menu range!");
        }
        return result;
    }
}
