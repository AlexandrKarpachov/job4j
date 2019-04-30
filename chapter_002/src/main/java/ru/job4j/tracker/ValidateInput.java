package ru.job4j.tracker;

import java.util.List;

/**
 * This class validates the user input
 */
public class ValidateInput implements Input {

    private final Input input;

    public ValidateInput(Input input) {
        this.input = input;
    }

    @Override
    public String ask(String message) {
        return this.input.ask(message);
    }

    @Override
    public int ask(String question, List<Integer> range) {
        int result = -1;
        boolean isInvalid = true;
         do {
            try {
                result = this.input.ask(question, range);
                isInvalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter valid data");
            } catch (MenuOutException moe) {
                System.out.println("Please enter valid point");
            }
        } while (isInvalid);
        return result;
    }
}
