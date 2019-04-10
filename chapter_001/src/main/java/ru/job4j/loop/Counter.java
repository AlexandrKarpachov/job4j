package ru.job4j.loop;

public class Counter {

    /**
     * Method count a sum all of the even numbers, between two numbers
     * @param start number
     * @param finish number
     * @return int
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}