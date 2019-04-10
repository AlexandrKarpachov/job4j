package ru.job4j.calculate;
/**
 *Calculator
 * support only: add, subtract, div and multiple.
 * @version 0.01
 * @author Aleksandr Karpachov
 */

public class Calculator {
    /**
     * Method adds two values
     * @param first first value
     * @param second second value
     * @return result
     */

    public double add(double first, double second) {
        return first + second;
    }

    /**
     * Method subtracts two values
     * @param first value
     * @param second value
     * @return result
     */
    public double subtract(double first, double second) {
        return first - second;
    }

    /**
     * Method multiplies two values
     * @param first value
     * @param second value
     * @return result
     */
    public double multiply(double first, double second) {
        return first * second;
    }

    /**
     * Method divides two values
     * @param first first
     * @param second second
     * @return result. If first and second value are equal 0, return NaN,
     * else if first is positive and second == 0, return Infinity
     * else if first is negative and second == 0, return -Infinity
     */
    public double divide(double first, double second) {
        return first / second;
    }
}
