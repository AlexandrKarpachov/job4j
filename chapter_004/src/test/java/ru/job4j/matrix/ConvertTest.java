package ru.job4j.matrix;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConvertTest {
    @Test
    public void whenPutArrayThenGetList() {
        Integer[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        var expect = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        var convert = new Convert();
        var actual = convert.toList(matrix);
        assertThat(expect, is(actual));
    }

    @Test
    public void whenPutEmptyArrayThenGetEmptyList() {
        Integer[][] matrix = {{}};
        var expect = new ArrayList<Integer>();
        var convert = new Convert();
        var actual = convert.toList(matrix);
        assertThat(expect, is(actual));
    }
}
