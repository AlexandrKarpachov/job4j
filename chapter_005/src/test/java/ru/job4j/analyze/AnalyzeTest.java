package ru.job4j.analyze;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalyzeTest {
    Analyze analyze;
    List<Analyze.User> previous;
    List<Analyze.User> current;

    @Before
    public void fillingData() {
        analyze = new Analyze();
        List<Analyze.User> list = Arrays.asList(
                new Analyze.User(1, "first"),
                new Analyze.User(2, "second"),
                new Analyze.User(3, "third"),
                new Analyze.User(4, "four")
        );
        this.previous = new ArrayList<>(list);
        this.current = new ArrayList<>(list);

    }

    @Test
    public void whenAllDeletedThenDeleted4() {
        current = new ArrayList<>();

        var info = analyze.diff(previous, current);

        assertThat(info.deleted(), is(4));
    }

    @Test
    public void whenOneDeletedThenDeleted1() {
        current.remove(2);
        var info = analyze.diff(previous, current);

        assertThat(info.deleted(), is(1));
    }

    @Test
    public void whenAdd4ThenAdded4() {
        previous = new ArrayList<>();

        var info = analyze.diff(previous, current);

        assertThat(info.added(), is(4));
    }

    @Test
    public void whenOneChangedThenChanged1() {
        current.set(1, new Analyze.User(2, "five"));

        var info = analyze.diff(previous, current);

        assertThat(info.changed(), is(1));
    }

    @Test
    public void whenNoChangingThenAllFieldsZero() {
        var info = analyze.diff(previous, current);

        assertThat(info.added(), is(0));
        assertThat(info.deleted(), is(0));
        assertThat(info.changed(), is(0));
    }

    @Test
    public void whenOneChangeDeleteAndAddThenAllFieldsOne() {
        current.set(1, new Analyze.User(2, "five"));
        current.remove(3);
        current.add(new Analyze.User(5, "five"));

        var info = analyze.diff(previous, current);

        assertThat(info.added(), is(1));
        assertThat(info.deleted(), is(1));
        assertThat(info.changed(), is(1));
    }

}