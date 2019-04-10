package ru.job4j.condition;


import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

public class TriangleTest {
    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        Triangle triangle = new Triangle();
        double result = triangle.area(0, 0, 0, 2, 2, 0);
        double expected = 2D;
        assertThat(result, closeTo(expected, 0.1));
    }

    @Test
    public void whenAreaSetThreePointsThenTrianglePerimeter() {
        Triangle triangle = new Triangle();
        double result = triangle.period(5, 5, 5);
        double expected = 7.5D;
        assertThat(result, closeTo(expected, 0.1));
    }

    @Test
    public void whenTriangleDoesNotExist() {
        Triangle triangle = new Triangle();
        double result = triangle.area(10, 0, 2, 0, 3, 0);
        assertThat(result, is(-1D));
    }
}

