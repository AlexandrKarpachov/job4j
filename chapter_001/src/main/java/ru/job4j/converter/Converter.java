package ru.job4j.converter;

/**
 * Currency converter
 */
public class Converter {
    //Exchange Rates
    private final static int EURO = 70;
    private final static int DOLLAR = 60;

    /**
     * Convert rubles in euro.
     * @param value rubles.
     * @return euro.
     */
    public int rubleToEuro(int value) {
        return value / EURO;
    }

    /**
     * Convert rubles in dollars.
     * @param value rubles.
     * @return dollars.
     */
    public int rubleToDollar(int value) {
        return value / DOLLAR;
    }

    /**
     * Convert dollar in rubles.
     * @param value rubles.
     * @return dollars.
     */
    public int dollarToRuble(int value) {
        return value * DOLLAR;
    }

    /**
     * Convert dollar in rubles.
     * @param value rubles.
     * @return euro.
     */
    public int euroToRuble(int value) {
        return value * EURO;
    }

}