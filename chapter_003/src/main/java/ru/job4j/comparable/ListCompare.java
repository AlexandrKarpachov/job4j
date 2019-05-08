package ru.job4j.comparable;

import java.util.Comparator;

public class ListCompare implements Comparator<String> {
    /**
     * Compare two String in lexicographic order
     * @param left first String
     * @param right second String
     * @return negative number if left less then right, positive number
     * if left bigger then right and zero if they identical.
     */
    @Override
    public int compare(String left, String right) {
        int result = 0;
        int length = left.length() > right.length()
                ? right.length() : left.length();
        for (int i = 0; i < length; i++) {
            result = Character.compare(left.charAt(i), right.charAt(i));
            if (result != 0) {
                break;
            }
        }
        if (result == 0) {
            result = left.length() - right.length();
        }
        return result;
    }
}