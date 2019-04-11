package ru.job4j.array;

public class FindLoop {
    /**
     * Method searching element in array
     * @param data array for searching
     * @param el item to find
     * @return first index of {@param el} or -1 if it does not exists in array
     */
    public int indexOf(int[] data, int el) {
        int rst = -1; // если элемента нет в массиве, то возвращаем -1.
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}