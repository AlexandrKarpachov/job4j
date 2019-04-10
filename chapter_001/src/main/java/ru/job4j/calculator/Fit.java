package ru.job4j.calculator;

/**
 * Программа расчета идеального веса.
 */
public class Fit {
    //multiplier in ideal weight formula
    private static final double COEFFICIENT = 1.15;
    //value that we need to subtract from height
    private static final double FOR_MANS = 100;
    private static final double FOR_WOMEN = 100;

    /**
     * Ideal weight for man.
     * @param height Height.
     * @return ideal weight.
     */
    public double manWeight(double height) {
        return (height - FOR_MANS) * COEFFICIENT;
    }

    /**
     * Ideal weight for woman
     * @param height height.
     * @return ideal weight.
     */
    public double womanWeight(double height) {
        return (height - FOR_WOMEN) * COEFFICIENT;
    }
}
