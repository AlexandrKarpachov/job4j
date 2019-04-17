package ru.job4j.condition;

/**
 * Points class
 */
public class Point {
    private int x;
    private int y;
    public Point(int first, int second) {
        this.x = first;
        this.y = second;
    }
    /**
        * Method for solve distance between two points by coordinates (x, y)
     * @param that another Point
     * @return double
     */

    public double distance(Point that) {
        double first = Math.pow(this.x - that.x, 2);
        double second = Math.pow(this.y - that.y, 2);
        return Math.sqrt(first + second);
    }

    /**
     * this method prints x and y of this Point
     */
    public void info() {
        System.out.println(String.format("Point[%s, %s]", this.x, this.y));
    }
}

