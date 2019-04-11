package ru.job4j.array;

public class Square {
    /**
     * Method filing array square degrees numbers from 1 to bound
     * @param bound how much numbers to square
     * @return array with square degrees
     */
    public int[] calculate(int bound) {
    int[] arr = new int[bound];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (i + 1) * (i + 1);
        }
        return arr;
    }
}
