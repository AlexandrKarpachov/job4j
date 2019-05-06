package ru.job4j.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karpachov Aleksandr
 * the class realize method for divide number by set of values inputed in massive
 * in all possible ways/
 */
public class CashMachine {
    /**
     * this variable store dividers
     */
    private final int[] values;

    public CashMachine(final int[] values) {
        this.values = values;
        this.invertSort(values);
    }

    /**
     * main Method of class. Can throw WrongDividersSetException exception if
     * input value can't be exchanged by current @param values
     * @param note value for exchange
     * @return List with lists of all possible variants of exchange
     */
    public List<List<Integer>> exchange(int note) throws WrongDividersSetException {
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(divide(note, true));
        if (lists.get(0).isEmpty()) {
            throw new WrongDividersSetException();
        }
        return exchange(lists);
    }

    /**
     * this method exchanging list of notes exchanged for the minimum number of notes
     * by all possible variants
     */
    private List<List<Integer>> exchange(List<List<Integer>> lists) {
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).size(); j++) {
                List<Integer> list = new ArrayList<>();
                list.addAll(lists.get(i));
                List<Integer> divided = divide(lists.get(i).get(j), false);
                if (divided.size() > 0) {
                    list.addAll(divided);
                    list.remove(j);
                    lists.add(list);
                    break;
                }
            }
        }
        return lists;
    }

    /**
     * This method divides value by less notes or can return
     * list with minimum coun of notes for input value
     * If input value can't be exchanged by current set of dividers
     *  {@param value} It return empty List<Integer>.
     * @param num input value
     * @param isMin if this flag true method return min count of notes,
     *             or exchange note on less notes if it false
     */
    private List<Integer> divide(int num, boolean isMin) {
        List<Integer> res = new ArrayList<>();
        int index = isMin ? 0 : lessIndex(num);
        if (index >= 0) {
            for (int i = index; i < values.length; i++) {
                while (num >= values[i]) {
                    res.add(values[i]);
                    num -= values[i];
                }
            }
        }
        if (num != 0) {
            res = new ArrayList<>();
        }
        return res;
    }

    /**
     * @param note input note
     * @return index of less note then inputted in @param values
     */
    private int lessIndex(int note) {
        int result = -1;
        for (int i = 0; i < values.length; i++) {
            if (values[i] < note) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * This method sort input array from biggest value to min
     * There is using buble sort method
     * @param arr input arr.
     */
    private void invertSort(int[] arr) {
        int count = arr.length;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < count - 1; j++, count--) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}