package ru.job4j.array;

public class Check {
    public boolean mono(boolean[] data) {
        boolean result = true;
        if (data.length >= 1) {
            boolean first = data[0];
            for (boolean value : data) {
                if (value != first) {
                    result = false;
                    break;
                }
            }
        } else {
            result = false;
        }
        return result;
    }
}