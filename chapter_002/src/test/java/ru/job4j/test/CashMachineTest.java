package ru.job4j.test;

import org.junit.Test;

import java.util.List;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CashMachineTest {
    @Test
    public void unchange() {
        CashMachine machine = new CashMachine(new int[] {1, 5, 10});
        List<List<Integer>> result = machine.exchange(1);
        assertThat(result, is(singletonList(singletonList(1))));
    }

    @Test
    public void five() {
        CashMachine machine = new CashMachine(new int[] {1, 5, 10});
        List<List<Integer>> result = machine.exchange(5);
        assertThat(result, is(
                asList(
                        asList(5),
                        asList(1, 1, 1, 1, 1)
                )
        ));
    }

    @Test
    public void change() {
        CashMachine machine = new CashMachine(new int[] {10, 5, 1});
        List<List<Integer>> result = machine.exchange(10);
        assertThat(
                result, is(
                        asList(
                                singletonList(10),
                                asList(5, 5),
                                asList(5, 1, 1, 1, 1, 1),
                                asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
                        )
                )
        );
    }

    @Test
    public void change15() {
        CashMachine machine = new CashMachine(new int[] {10, 5, 1});
        List<List<Integer>> result = machine.exchange(15);
        assertThat(
                result, is(
                        asList(
                                asList(10, 5),
                                asList(5, 5, 5),
                                asList(5, 5, 1, 1, 1, 1, 1),
                                asList(5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
                                asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
                        )
                )
        );
    }

    @Test(expected = WrongDividersSetException.class)
    public void incorrectSet() {
        CashMachine machine = new CashMachine(new int[] {10, 5});
        List<List<Integer>> result = machine.exchange(12);
    }
}