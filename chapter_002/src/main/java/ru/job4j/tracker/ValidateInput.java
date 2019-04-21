package ru.job4j.tracker;

import java.util.List;

/**
 * This class validates the user input
 */
public class ValidateInput extends ConsoleInput {

    public int ask(String question, List<Integer> range) {
        int result = -1;
        boolean isInvalid = true;
         do {
            try {
                result = super.ask(question, range);
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
