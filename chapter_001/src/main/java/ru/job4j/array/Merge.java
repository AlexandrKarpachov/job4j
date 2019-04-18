package ru.job4j.array;
public class Merge {
    public int[] merge(int[] left, int[] right) {
        int[] rsl = new int[left.length + right.length];
        int l = 0, r = 0;
        for (int i = 0; i < rsl.length; i++) {
            if (l >= left.length) {
                System.arraycopy(right, r, rsl, i, right.length - r);
                break;
            } else if (r >= right.length) {
                System.arraycopy(right, l, rsl, i, right.length - l);
                break;
            } else if (left[l] < right[r]) {
                rsl[i] = left[l];
                l++;
            } else {
                rsl[i] = right[r];
                r++;
            }
        }
        return rsl;
    }
}