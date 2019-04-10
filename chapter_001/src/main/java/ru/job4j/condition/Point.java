package ru.job4j.condition;

/**
 * Class for make solves with points
 */
public class Point {
    /**
     * Method for solve distance between two points by coordinates (x, y)
     * @param x1 coordinate x of first point,
     * @param y1 coordinate y of first point,
     * @param x2 coordinate x of second point,
     * @param y2 coordinate y of second point,
     * @return double
     */

    public double distance(int x1, int y1, int x2, int y2) {
        double first = Math.pow(x2 - x1, 2);
        double second = Math.pow(y1 - y2, 2);
        return Math.sqrt(first + second);
    }
}