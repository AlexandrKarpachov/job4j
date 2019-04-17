package ru.job4j.condition;

public class Triangle {
    private Point first;
    private Point second;
    private Point third;

    public Triangle(Point ap, Point bp, Point cp) {
        this.first = ap;
        this.second = bp;
        this.third = cp;
    }

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
    public double area() {
        double rsl = -1;
        double a = this.first.distance(this.second);
        double b = this.second.distance(this.third);
        double c = this.first.distance(this.third);
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