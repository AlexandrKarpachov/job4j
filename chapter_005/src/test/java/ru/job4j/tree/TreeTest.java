package ru.job4j.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TreeTest {
    Tree<Integer> tree = new Tree<>(1);

    @Before
    public void fillingTree() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
    }
    @Test
    public void when6ElFindLastThen6() {
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void iteratorTest() {
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> actual = new ArrayList<>();
        Iterator<Integer> it = tree.iterator();

        while (it.hasNext()) {
            actual.add(it.next());
        }

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTreeNotBinaryThenFalse() {
        assertThat(tree.isBinary(), is(false));
    }

    @Test
    public void whenTreeBinaryThenIsBinaryTrue() {
        tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(5, 6);
        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void whenAddNewNodeWithNonExistsParentThenNewRoot() {
        tree.add(7, 8);
        List<Integer> expected = List.of(7, 8, 1, 2, 3, 4, 5, 6);
        List<Integer> actual = new ArrayList<>();

        for (int entry : tree) {
            actual.add(entry);
        }

        assertThat(actual, is(expected));
    }


}