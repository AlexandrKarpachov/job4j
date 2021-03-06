package ru.job4j.test;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DepartmentsTest {
    @Test
    public void whenMissed() {
        Departments deps = new Departments();
        List<String> input = List.of("k1/sk1");
        List<Departments.Org> expect = Arrays.asList(
                new Departments.Org(List.of("k1")),
                new Departments.Org(List.of("k1", "sk1"))
        );
        List<Departments.Org> result = deps.convert(input);
        assertThat(result, is(expect));
    }

    @Test
    public void whenAsc() {
        Departments deps = new Departments();
        List<String> input = List.of("k1/sk1", "k2", "k1/sk1/ssk1");
        List<Departments.Org> expect = List.of(
                new Departments.Org(List.of("k1")),
                new Departments.Org(List.of("k1", "sk1")),
                new Departments.Org(List.of("k1", "sk1", "ssk1")),
                new Departments.Org(List.of("k2"))
        );
        List<Departments.Org> result = deps.sortAsc(deps.convert(input));
        assertThat(result, is(expect));
    }

    @Test
    public void whenDesc() {
        Departments deps = new Departments();
        List<String> input = List.of("k1/sk1", "k2");
        List<Departments.Org> expect = List.of(
                new Departments.Org(List.of("k2")),
                new Departments.Org(List.of("k1")),
                new Departments.Org(List.of("k1", "sk1"))
        );
        List<Departments.Org> result = deps.sortDesc(deps.convert(input));
        assertThat(result, is(expect));
    }

    @Test
    public void whenDesc3() {
        Departments deps = new Departments();
        List<String> input = List.of("K1/SK1/SSK2", "K2/SK1/SSK2", "K2/SK1/SSK1");
        List<Departments.Org> expect = List.of(
                new Departments.Org(List.of("K2")),
                new Departments.Org(List.of("K2", "SK1")),
                new Departments.Org(List.of("K2", "SK1", "SSK2")),
                new Departments.Org(List.of("K2", "SK1", "SSK1")),
                new Departments.Org(List.of("K1")),
                new Departments.Org(List.of("K1", "SK1")),
                new Departments.Org(List.of("K1", "SK1", "SSK2"))
        );
        List<Departments.Org> result = deps.sortDesc(deps.convert(input));
        assertThat(result, is(expect));
    }
}