package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class Functions {
    public static List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int i = start; i < end; i++) {
            result.add(func.apply((double) i));
        }
        return result;
    }

    public static List<Double> diapason(int start, int end, double value,
                                        BiFunction<Double, Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int i = start; i < end; i++) {
            result.add(func.apply((double) i, value));
        }
        return result;
    }
}
