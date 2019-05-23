package ru.job4j.tictactoe;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * The {@code Logic3T} class describes the logic of the TicTakToe game.
 *
 * @author Alexander Karpachov
 * @version 1.0
 * @since 29.05.2019
 */
public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * @param predicate Predicate Function for Figures.
     * @param startX    X Coordinate of Start Position.
     * @param startY    Y Coordinate of Start Position.
     * @param deltaX    Direction of changing X Coordinate.
     * @param deltaY    Direction of changing Y Coordinate.
     * @return true - if the column/row/diagonal is filled by the Figure X or The Figure O; otherwise - false;
     */
    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Method checks if X won
     */
    public boolean isWinnerX() {
        return isDiagonal(Figure3T::hasMarkX) || isLine(Figure3T::hasMarkX);
    }

    /**
     * Method checks if X won
     */
    public boolean isWinnerO() {
        return isDiagonal(Figure3T::hasMarkO) || isLine(Figure3T::hasMarkO);
    }

    /**
     * Method checks to make sure if empty cells are left.
     */
    public boolean hasGap() {
        return Arrays.stream(this.table).flatMap(Arrays::stream)
                .anyMatch(Figure3T::isEmpty);
    }

    /**
     * Method checks if  one of figures won by line or column.
     * @param predicate that checks that the figure already exists
     * @return true if figure won by line or vertical line, otherwise false
     */
    private boolean isLine(Predicate<Figure3T> predicate) {
        boolean result = false;
        for (int i = 0; i < this.table.length; i++) {
            if (this.fillBy(predicate, i, 0, 0, 1)
                    || this.fillBy(predicate, 0, i, 1, 0)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Method checks if  one of figures won by line or column.
     * @param figurePredicate that checks existing of current figure
     * @return true if figure won by diagonal, otherwise false
     */
    private boolean isDiagonal(Predicate<Figure3T> figurePredicate) {
        return this.fillBy(figurePredicate, this.table.length - 1, 0, -1, 1)
                || this.fillBy(figurePredicate, 0, 0, 1, 1);
    }
}
