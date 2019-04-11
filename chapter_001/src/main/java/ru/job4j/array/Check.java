package ru.job4j.array;

public class Check {
    public boolean mono(boolean[] data) {
        boolean result = true;
        boolean first;
        if (data.length >= 1) {
            first = data[0];
        } else {
            return false;
        }
        for (boolean value : data) {
            if (value != first) {
                return false;
            }
        }
        return result;
    }
}