package ru.job4j.array;

import java.util.Arrays;

public class ArrayDuplicate {
    /**
     * Method remoove all duplicate from array.
     * @param array for handling
     * @return new array without duplicates
     */
    public String[] remove(String[] array) {
        String[] result;
        if (array.length <= 1) {
            result = Arrays.copyOf(array, array.length);
        } else {
            int count = array.length - 1;
            for (int i = 0; i < count; i++) {
                for (int j = i + 1; j < count; j++) {
                    if (array[i].equals(array[j])) {
                        array[j] = array[count];
                        count--;
                    }
                }
                if (array[i].equals(array[count])) {
                    count--;
                }
            }
            result = Arrays.copyOf(array, count + 1);
        }
        return result;
    }
}
