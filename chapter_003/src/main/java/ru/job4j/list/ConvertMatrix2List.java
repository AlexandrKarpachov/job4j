package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * convert two-dimensional array to list.
 */
public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for(int[] subArr : array) {
            for (int num : subArr) {
                list.add(num);
            }
        }
        return list;
    }
}