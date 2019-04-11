package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SquareTest {
    @Test
    public void squareWhenBound3Then149() {
        Square test = new Square();
        int[] actual = test.calculate(3);
        int[] expect = new int[] {1, 4, 9};
        assertThat(expect, is(actual));
    }

    @Test
    public void squareWhenBound0Then1() {
        Square test = new Square();
        int[] actual = test.calculate(0);
        int[] expect = new int[] {};
        assertThat(expect, is(actual));
    }

    @Test
    public void squareWhenBound5Then1491625() {
        Square test = new Square();
        int[] actual = test.calculate(5);
        int[] expect = new int[] {1, 4, 9, 16, 25};
        assertThat(expect, is(actual));
    }
}
