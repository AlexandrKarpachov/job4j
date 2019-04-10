package ru.job4j.calculate;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class CalculatorTest {
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        double result = calc.add(1D, 1D);
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenSubtractTwoMinusOneThenOne() {
        Calculator calc = new Calculator();
        double result = calc.subtract(2D, 1D);
        double expected = 1D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenMultiplyTwoOnTwoThenFour() {
        Calculator calc = new Calculator();
        double result = calc.multiply(2D, 2D);
        double expected = 4D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenDivide5On2Then2AndHalf() {
        Calculator calc = new Calculator();
        double result = calc.divide(5D, 2D);
        double expected = 2.5;
        assertThat(result, is(expected));
    }

    @Test
    public void whenDivide0onPositiveThenInfinity() {
        Calculator calc = new Calculator();
        double result = calc.divide(5D, 0);
        boolean isInfinity = Double.isInfinite(result);
        assertThat(isInfinity, is(true));
    }

    @Test
    public void whenDivide0on0ThenNaN() {
        Calculator calc = new Calculator();
        double result = calc.divide(0, 0);
        boolean resultIsNaN = Double.isNaN(result);
        assertThat(resultIsNaN, is(true));
    }


}
