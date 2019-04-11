package ru.job4j.loop;
/**
 *
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 0.1
 */
public class Board {
    /**
     * Method for painting chess board
     * @param width of board
     * @param height of board
     * @return String in "X X" format
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}
