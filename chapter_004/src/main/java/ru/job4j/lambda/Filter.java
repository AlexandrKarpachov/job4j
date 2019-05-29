package ru.job4j.lambda;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Alexander Karpachov
 * @since 29.05.19
 */
public class Filter {

    /**
     * Method squares all the even numbers and then sums them up
     * @param arr input array
     */
    public int sumOfEvenSqrs(int[] arr) {
        return Arrays.stream(arr)
                .filter(i -> i % 2 == 0)
                .map(i -> i * i)
                .reduce(0, Integer::sum);
    }

    /**
     * Checks if input words are anagrams to each other
     * @param first word
     * @param second word
     * @return true if second word is anagram to first word. Otherwise: false.
     */
    public boolean isAnagram(String first, String second) {
       return this.getCharSet(first).equals(this.getCharSet(second));
    }

    private String getCharSet(String word) {
        return word.toLowerCase()
                .codePoints()
                .sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
