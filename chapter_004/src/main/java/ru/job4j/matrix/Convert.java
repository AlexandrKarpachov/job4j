package ru.job4j.matrix;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aleksand Karpachov
 * @since 22.05.2019
 */
public class Convert {
    /**
     * method converts double array of Integers to list
     * @param matrix input
     * @return list with all values that store in array.
     */
    public List<Integer> toList(Integer[][] matrix) {
        return Arrays.stream(matrix)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }
}
