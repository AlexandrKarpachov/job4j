package ru.job4j.lambda;

import java.util.Arrays;

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
}
