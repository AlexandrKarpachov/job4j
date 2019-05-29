package ru.job4j.lambda;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FilterTest {
    @Test
    public void whenEmptyArray() {
        var arr = new int[0];
        var filter = new Filter();
        var result = filter.sumOfEvenSqrs(arr);
        assertThat(0, is(result));
    }

    @Test
    public void whenTwoEven() {
        var arr = new int[] {2, 3, 6};
        var filter = new Filter();
        var result = filter.sumOfEvenSqrs(arr);
        assertThat(result, is(40));
    }

    @Test
    public void whenAnyEven() {
        var arr = new int[] {1, 3, 5};
        var filter = new Filter();
        var result = filter.sumOfEvenSqrs(arr);
        assertThat(0, is(result));
    }
}
