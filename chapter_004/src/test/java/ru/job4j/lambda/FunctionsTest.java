package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class FunctionsTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = Functions.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSquareFunctionThenSquareResults() {
        List<Double> result = Functions.diapason(2, 5,
                x -> x * x + 2 * x);
        List<Double> expected = Arrays.asList(8D, 15D, 24D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogFunctionThenLogResults() {
        List<Double> result = Functions.diapason(4, 8, 2,
                (x, y) -> y * Math.sqrt(x));
        List<Double> expected = Arrays.asList(4D, 4.47D, 4.89D, 5.29D);
        assertThat(expected.size(), is(result.size()));
        for (int i = 0; i < expected.size(); i++) {
            assertThat(result.get(i), closeTo(expected.get(i), 0.01));
        }
    }

}
