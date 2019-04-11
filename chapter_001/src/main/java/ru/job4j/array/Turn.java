package ru.job4j.array;

public class Turn {
    /**
     * Method  for inverting array
     * @param array to reverse
     */
    public int[] back(int[] array) {
        int temp, index;
        for (int i = 0; i < array.length / 2; i++) {
            temp = array[i];
            index = array.length - 1 - i;
            array[i] = array[index];
            array[index] = temp;
        }
        return array;
    }
}
