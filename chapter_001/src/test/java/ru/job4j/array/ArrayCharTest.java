package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayCharTest {
    @Test
    public void whenStartWithPrefixThenTrue() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("He");
        assertThat(result, is(true));
    }

    @Test
    public void whenNotStartWithPrefixThenFalse() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("Hi");
        assertThat(result, is(false));
    }


        @Test
        public void whenPrefixLongerThenWord() {
            ArrayChar word = new ArrayChar("He");
            boolean result = word.startWith("Hello");
            assertThat(result, is(true));
        }

    @Test
    public void whenPrefixEqualsWord() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("Hello");
        assertThat(result, is(true));
    }
}