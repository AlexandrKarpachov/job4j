package ru.job4j.loop;



public class Paint {

    /**
     * This method points right part of the pyramid
     * @param height of the pyramid
     * @return String with triangle
     */
    public String rightTrl(int height) {
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < height; column++) {
                if (row >= column) {
                    result.append("^");
                } else {
                    result.append(" ");
                }
            }
            result.append(System.lineSeparator());
        }
        return result.toString();
    }
    /**
     * This method points left part of the pyramid
     * @param height of the pyramid
     * @return String with triangle
     */
    public String leftTrl(int height) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {
                if (j >= height - i - 1) {
                    result.append("^");
                } else {
                    result.append(" ");
                }
            }
            result.append(System.lineSeparator());
        }
        return result.toString();
    }

    /**
     * This method points right part of the pyramid
     * @param height of the pyramid
     * @return String with pyramid
     */
    public String pyramid(int height) {
        StringBuilder screen = new StringBuilder();
        int width = 2 * height - 1;
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (row >= height - column - 1 && row + height - 1 >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}
