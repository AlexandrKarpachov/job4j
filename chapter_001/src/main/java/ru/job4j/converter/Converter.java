package ru.job4j.converter;

/**
 * Currency converter
 */
public class Converter {

    private final static int COURSE_EURO = 70;
    private final static int COURSE_DOLLAR = 60;

    /**
     * Convert rubles in euro.
     * @param value rubles.
     * @return euro.
     */
    public int rubleToEuro(int value) {
        return value / COURSE_EURO;
    }

    /**
     * Convert rubles in dollars.
     * @param value rubles.
     * @return dollars.
     */
    public int rubleToDollar(int value) {
        return value / COURSE_DOLLAR;
    }

    /**
     * Convert dollar in rubles.
     * @param value rubles.
     * @return dollars.
     */
    public int dollarToRuble(int value) {
        return value * COURSE_DOLLAR;
    }

    /**
     * Convert dollar in rubles.
     * @param value rubles.
     * @return euro.
     */
    public int euroToRuble(int value) {
        return value * COURSE_EURO;
    }

}