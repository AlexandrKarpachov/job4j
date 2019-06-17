package ru.job4j.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RunnerTest {
    Runner runner = new Runner();
    @Test
    public void whenLoadScriptWith6DependenciesThenListWithRightOder() {
        Map<Integer, List<Integer>> ds = new HashMap<>();
        List<Integer> expected = List.of(7, 4, 5, 2, 6, 3);
        ds.put(1, List.of(2, 3));
        ds.put(2, List.of(4, 5));
        ds.put(3, List.of(4, 6));
        ds.put(4, List.of(7));

        List<Integer> result = runner.load(ds, 1);

        assertThat(result, is(expected));
    }

    @Test
    public void whenLoadScriptWithoutDependenciesThenEmptyList() {
        Map<Integer, List<Integer>> ds = new HashMap<>();

        List<Integer> result = runner.load(ds, 1);
        assert (result.isEmpty());
    }
}