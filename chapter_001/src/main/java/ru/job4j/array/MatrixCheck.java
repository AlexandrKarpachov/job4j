package ru.job4j.array;

public class MatrixCheck {
    /**
     * Method check that all elements in diagonals equals true or false
     * @param data input array
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        boolean main = data[0][0];
        boolean back = data[data.length - 1][0];
        for (int i = 0; i < data.length; i++) {
            if (data[i][i] != main || data[data.length - i - 1][i] != back) {
                return false;
            }
        }
        return result;
    }
}
