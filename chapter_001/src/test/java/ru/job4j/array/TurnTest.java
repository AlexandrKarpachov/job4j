package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TurnTest {
    @Test
    public void whenEvenSizeArray() {
        Turn test = new Turn();
        int[] actual = test.back(new int[] {0, 2, 4, 6});
        int[] expected = new int[] {6, 4, 2, 0};
        assertThat(expected, is(actual));
    }

    @Test
    public void whenNotEvenSizeArray() {
        Turn test = new Turn();
        int[] actual = test.back(new int[] {0, 2, 4, 6, 7});
        int[] expected = new int[] {7, 6, 4, 2, 0};
        assertThat(expected, is(actual));
    }
}
