package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class NodeTest {

    @Test
    public void whenLastReferToFirstThanTrue() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        boolean actual = first.hasCycle(first);

        assertThat(actual, is(true));
    }

    @Test
    public void whenLastReferToMiddleThanTrue() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = third;
        boolean actual = first.hasCycle(first);

        assertThat(actual, is(true));
    }

    @Test
    public void whenDoesNotLoopThanFalse() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        first.next = two;
        two.next = third;
        third.next = four;
        boolean actual = first.hasCycle(first);

        assertThat(actual, is(false));
    }

    @Test
    public void whenLastReferToSecondThanTrue() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);
        Node<Integer> five = new Node<>(5);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = five;
        five.next = two;
        boolean actual = first.hasCycle(first);

        assertThat(actual, is(true));
    }

    @Test
    public void whenFiveElementsWithoutLoopThanFalse() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);
        Node<Integer> five = new Node<>(5);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = five;


        boolean actual = first.hasCycle(first);

        assertThat(actual, is(false));
    }
}