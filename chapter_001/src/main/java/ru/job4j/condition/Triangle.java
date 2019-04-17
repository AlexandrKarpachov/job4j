package ru.job4j.condition;

public class Triangle {

    /**
     * Метод вычисления периметра по длинам сторон.
     *
     * @param a distance between points a b
     * @param b distance between points a c
     * @param c distance between points b c
     * @return perimeter.
     */
    public double period(double a, double b, double c) {
        return (a + b + c) / 2;
    }

    /**
     * Method solve triangle area
     *
     * @return area if triangle exist or -1 if it doesn't.
     */
    public double area(int x1, int y1, int x2, int y2, int x3, int y3) {
        double rsl = -1;
        double a = new Point(x1, y1).distance(new Point(x2, y2));
        double b = new Point(x2, y2).distance(new Point(x3, y3));
        double c = new Point(x1, y1).distance(new Point(x3, y3));
        double p = period(a, b, c);
        if (this.exist(a, b, c)) {
           rsl = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        }
        return rsl;
    }

    /**
     * Method checks existence of triangle
     *
     * @param a distance between points a b
     * @param b distance between points a c
     * @param c distance between points b c
     */
    private boolean exist(double a, double c, double b) {
        return !(a >= b + c || b >= a + c || c >= b + a);
    }
}