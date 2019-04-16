package ru.job4j.array;

public class Square {
    /**
     * Method filing array square degrees numbers from 1 to bound
     * @param bound how much numbers to square
     * @return array with square degrees
     */
    public int[] calculate(int bound) {
    int[] result = new int[bound];
        for (int i = 0; i < result.length; i++) {
            result[i] = (i + 1) * (i + 1);
        }
        return result;
    }
}
